package template.android.com.device.crypto.provider;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import template.android.com.domain.crypto.CommonCryptoConstants;
import template.android.com.domain.crypto.provider.SymmetricKeyProvider;
import template.android.com.domain.crypto.provider.SymmetricKeyProviderException;

@TargetApi(Build.VERSION_CODES.M)
public final class SymmetricKeyProviderV23Impl implements SymmetricKeyProvider {

    private static final String ANDROID_KEY_STORE = CommonCryptoConstants.ANDROID_KEY_STORE;

    private static final String ALGORITHM_AES = "AES";

    private static final int AES_KEY_SIZE = 1 << 7;

    private final KeyStore keyStore;

    public SymmetricKeyProviderV23Impl(final KeyStore keyStore) {
        this.keyStore = keyStore;

        try {
            this.keyStore.load(null);

        } catch (final Exception e) {
            throw new SymmetricKeyProviderException(e);
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    @Override
    public SecretKey getSymmetricAesCbcPkcs7PaddingKeyForAlias(final String alias) {

        try {
            if (!keyStore.containsAlias(alias)) {

                try {
                    final KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES, ANDROID_KEY_STORE);
                    keyGenerator.init(getAesKeyParameterSpec(alias));

                    return keyGenerator.generateKey();

                } catch (final Exception e) {
                    throw new SymmetricKeyProviderException(e);
                }
            }

        } catch (final KeyStoreException e) {
            throw new SymmetricKeyProviderException(e);
        }

        try {
            return KeyStore.SecretKeyEntry.class.cast(keyStore.getEntry(alias, null))
                                                .getSecretKey();

        } catch (final NoSuchAlgorithmException e) {
            throw new SymmetricKeyProviderException(e);

        } catch (final UnrecoverableEntryException e) {
            throw new SymmetricKeyProviderException(e);

        } catch (final KeyStoreException e) {
            throw new SymmetricKeyProviderException(e);
        }
    }

    private KeyGenParameterSpec getAesKeyParameterSpec(final String alias) {

        final KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT);

        return builder.setKeySize(AES_KEY_SIZE)
                      .setBlockModes(KeyProperties.BLOCK_MODE_CBC, KeyProperties.BLOCK_MODE_GCM)
                      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7, KeyProperties.ENCRYPTION_PADDING_NONE)
                      .build();
    }

    @Override
    public void deleteSymmetricKey(final String alias) {
        try {
            keyStore.deleteEntry(alias);

        } catch (final KeyStoreException e) {
            throw new SymmetricKeyProviderException(e);
        }
    }

    @Override
    public void deleteAllKeys() {
        // TODO
    }
}

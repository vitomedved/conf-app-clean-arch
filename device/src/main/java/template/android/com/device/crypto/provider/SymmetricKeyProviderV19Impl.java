package template.android.com.device.crypto.provider;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

import template.android.com.domain.crypto.provider.SymmetricKeyProvider;
import template.android.com.domain.utils.numeric.ArrayUtils;
import template.android.com.domain.utils.time.CurrentTimeProvider;

public final class SymmetricKeyProviderV19Impl implements SymmetricKeyProvider {

    private static final String ALIAS_NAME_TEMPLATE = "CN=%s";
    private static final String KEY_ALGORITHM_RSA = "RSA";
    private static final String KEY_ALGORITHM_AES = "AES";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String RSA_NO_DIGEST_PKCS_1_PADDING = "RSA/NONE/PKCS1Padding";

    private static final int AES_KEY_SIZE = 1 << 7;
    private static final int RSA_KEY_SIZE = 2048;

    private static final long RSA_KEY_DURATION_IN_DAYS = 10_000L;

    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final KeyStore keyStore;
    private final SymmetricKeyProviderStorage symmetricKeyProviderStorage;

    private Cipher encryptCipher;
    private final Object encryptCipherLock = new Object();

    private Cipher decryptCipher;
    private final Object decryptCipherLock = new Object();

    public SymmetricKeyProviderV19Impl(final Context context, final CurrentTimeProvider currentTimeProvider, final KeyStore keyStore,
                                       final SymmetricKeyProviderStorage symmetricKeyProviderStorage) {
        this.context = context;
        this.currentTimeProvider = currentTimeProvider;
        this.keyStore = keyStore;
        this.symmetricKeyProviderStorage = symmetricKeyProviderStorage;

        try {
            this.keyStore.load(null);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    @Override
    public SecretKey getSymmetricAesCbcPkcs7PaddingKeyForAlias(final String alias) {

        if (!symmetricKeyProviderStorage.isKeySavedInStorage(alias)) {

            try {
                if (keyStore.containsAlias(alias)) {
                    keyStore.deleteEntry(alias);
                }

            } catch (final KeyStoreException e) {
                throw new RuntimeException(e);
            }

            generateKeyInternal(alias);
        }

        try {
            final KeyStore.PrivateKeyEntry privateKeyEntry = KeyStore.PrivateKeyEntry.class.cast(keyStore.getEntry(alias, null));

            final byte[] decryptedKey;
            synchronized (decryptCipherLock) {

                final byte[] rawKey = symmetricKeyProviderStorage.getKeyFromStorage(alias);

                decryptedKey = getDecryptCipher(privateKeyEntry.getPrivateKey(), rawKey);
            }

            return new SecretKeySpec(decryptedKey, KEY_ALGORITHM_AES);

        } catch (final BadPaddingException e) {
            throw new RuntimeException(e);

        } catch (final KeyStoreException e) {
            throw new RuntimeException(e);

        } catch (final IllegalBlockSizeException e) {
            throw new RuntimeException(e);

        } catch (final InvalidKeyException e) {
            throw new RuntimeException(e);

        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        } catch (final NoSuchPaddingException e) {
            throw new RuntimeException(e);

        } catch (final UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private void generateKeyInternal(final String alias) {

        try {
            generatePublicPrivateKeyForAliasInKeystore(alias);

            final byte[] rawKey = generateAesKey();

            final KeyStore.PrivateKeyEntry privateKeyEntry = KeyStore.PrivateKeyEntry.class.cast(keyStore.getEntry(alias, null));

            final byte[] cryptedKey = encryptKey(privateKeyEntry.getCertificate(), rawKey);

            ArrayUtils.clearArray(rawKey);

            symmetricKeyProviderStorage.saveKeyToPersistentStorage(alias, cryptedKey);

        } catch (final BadPaddingException e) {
            throw new RuntimeException(e);

        } catch (final IllegalBlockSizeException e) {
            throw new RuntimeException(e);

        } catch (final InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);

        } catch (final InvalidKeyException e) {
            throw new RuntimeException(e);

        } catch (final KeyStoreException e) {
            throw new RuntimeException(e);

        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        } catch (final NoSuchPaddingException e) {
            throw new RuntimeException(e);

        } catch (final NoSuchProviderException e) {
            throw new RuntimeException(e);

        } catch (final UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generateAesKey() {

        final SecureRandom random = new SecureRandom();

        final byte[] rawKey = new byte[AES_KEY_SIZE];
        random.nextBytes(rawKey);

        return rawKey;
    }

    private void generatePublicPrivateKeyForAliasInKeystore(final String alias) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA, ANDROID_KEY_STORE);
        keyPairGenerator.initialize(createKeyPairGeneratorSpe(alias));
        keyPairGenerator.generateKeyPair();
    }

    private byte[] encryptKey(final Certificate certificate, final byte[] rawKey) throws InvalidKeyException, NoSuchPaddingException,
                                                                                         NoSuchAlgorithmException, BadPaddingException,
                                                                                         IllegalBlockSizeException {

        synchronized (encryptCipherLock) {
            if (encryptCipher == null) {
                encryptCipher = Cipher.getInstance(RSA_NO_DIGEST_PKCS_1_PADDING);
            }

            encryptCipher.init(Cipher.ENCRYPT_MODE, certificate);

            return encryptCipher.doFinal(rawKey);
        }
    }

    private byte[] getDecryptCipher(final PrivateKey privateKey, final byte[] cryptedKey) throws BadPaddingException, IllegalBlockSizeException,
                                                                                                 InvalidKeyException, NoSuchPaddingException,
                                                                                                 NoSuchAlgorithmException {

        synchronized (decryptCipherLock) {
            if (decryptCipher == null) {
                decryptCipher = Cipher.getInstance(RSA_NO_DIGEST_PKCS_1_PADDING);
            }

            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

            return decryptCipher.doFinal(cryptedKey);
        }
    }

    private KeyPairGeneratorSpec createKeyPairGeneratorSpe(final String alias) {

        return new KeyPairGeneratorSpec.Builder(context).setAlias(alias)
                                                        .setSubject(getX509Principal(alias))
                                                        .setSerialNumber(getCertificateSerialNumber())
                                                        .setAlgorithmParameterSpec(getRsaKeyGenParameterSpec())
                                                        .setStartDate(getStartDate())
                                                        .setEndDate(getEndDate())
                                                        .build();
    }

    private X500Principal getX509Principal(final String alias) {
        return new X500Principal(String.format(ALIAS_NAME_TEMPLATE, alias));
    }

    private BigInteger getCertificateSerialNumber() {
        return BigInteger.valueOf(currentTimeProvider.getCurrentTimeMillis());
    }

    private RSAKeyGenParameterSpec getRsaKeyGenParameterSpec() {
        return new RSAKeyGenParameterSpec(RSA_KEY_SIZE, RSAKeyGenParameterSpec.F0);
    }

    private Date getStartDate() {
        return new Date(currentTimeProvider.getCurrentTimeMillis());
    }

    private Date getEndDate() {
        return new Date(currentTimeProvider.getCurrentTimeMillis() + TimeUnit.DAYS.toMillis(RSA_KEY_DURATION_IN_DAYS));
    }

    @Override
    public void deleteSymmetricKey(final String alias) {
        symmetricKeyProviderStorage.deleteKey(alias);
    }

    @Override
    public void deleteAllKeys() {
        symmetricKeyProviderStorage.deleteAllKeys();
    }
}

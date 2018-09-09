package template.android.com.device.crypto;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.List;

import template.android.com.domain.crypto.SecureKeystore;

public final class SecureKeystoreImpl implements SecureKeystore {

    private final KeyStore keyStore;

    public SecureKeystoreImpl(final KeyStore keyStore) {
        this.keyStore = keyStore;

        try {
            this.keyStore.load(null);

        } catch (final IOException e) {
            throw new SecureKeystoreException(e);

        } catch (final NoSuchAlgorithmException e) {
            throw new SecureKeystoreException(e);

        } catch (final CertificateException e) {
            throw new SecureKeystoreException(e);
        }
    }

    @Override
    public List<String> listAliases() {

        try {
            return Collections.list(keyStore.aliases());

        } catch (final KeyStoreException e) {
            throw new SecureKeystoreException(e);
        }
    }

    /**
     * Checks whether key stored under <code>alias</code> exists in underlying keystore.
     * <p>
     * If any error occurs, {@link template.android.com.domain.crypto.SecureKeystore.SecureKeystoreException} is thrown.
     * </p>
     *
     * @param alias to check existence for.
     * @return true if exists, false otherwise.
     */
    @Override
    public boolean containsAlias(final String alias) {
        try {
            return keyStore.containsAlias(alias);

        } catch (final KeyStoreException e) {
            throw new SecureKeystoreException(e);
        }
    }

    @Override
    public void removeAlias(final String alias) {
        try {
            keyStore.deleteEntry(alias);

        } catch (final KeyStoreException e) {
            throw new SecureKeystoreException(e);
        }
    }

    @Override
    public Key getSecretKeyWithAlias(final String alias) {
        try {
            return keyStore.getKey(alias, null);

        } catch (final NoSuchAlgorithmException e) {
            throw new SecureKeystoreException(e);

        } catch (UnrecoverableEntryException e) {
            throw new SecureKeystoreException(e);

        } catch (KeyStoreException e) {
            throw new SecureKeystoreException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void createKey() {

    }
}

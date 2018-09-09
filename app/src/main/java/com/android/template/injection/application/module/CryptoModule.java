package com.android.template.injection.application.module;

import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.crypto.SecureKeystoreImpl;
import template.android.com.domain.crypto.SecureKeystore;

@Module
public final class CryptoModule {

    @Provides
    @Singleton
    @AndroidKeyStore
    public SecureKeystore provideAndroidSecureKeystore() {
        try {
            return new SecureKeystoreImpl(KeyStore.getInstance("AndroidKeyStore"));

        } catch (final KeyStoreException e) {
            throw new RuntimeException("Could not load keystore", e);
        }
    }

    public interface Exposes {

        @AndroidKeyStore
        SecureKeystore androidSecureKeystore();
    }

    @Qualifier
    public @interface AndroidKeyStore { }
}

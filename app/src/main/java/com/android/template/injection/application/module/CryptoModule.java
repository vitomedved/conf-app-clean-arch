package com.android.template.injection.application.module;

import android.content.Context;

import com.android.template.injection.ForApplication;

import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.crypto.keystore.SecureKeystoreImpl;
import template.android.com.device.crypto.provider.SymmetricKeyProviderStorage;
import template.android.com.device.crypto.provider.SymmetricKeyProviderStorageImpl;
import template.android.com.device.crypto.provider.SymmetricKeyProviderV19Impl;
import template.android.com.device.crypto.provider.SymmetricKeyProviderV23Impl;
import template.android.com.domain.crypto.digest.MessageDigestFactory;
import template.android.com.domain.crypto.digest.MessageDigestFactoryImpl;
import template.android.com.domain.crypto.keystore.SecureKeystore;
import template.android.com.domain.crypto.obfuscator.StringObfuscator;
import template.android.com.domain.crypto.obfuscator.StringObfuscatorImpl;
import template.android.com.domain.crypto.provider.SymmetricKeyProvider;
import template.android.com.domain.device.DeviceInformation;
import template.android.com.domain.utils.time.CurrentTimeProvider;

@Module
public final class CryptoModule {

    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";

    @Provides
    @Singleton
    MessageDigestFactory provideMessageDigestFactory() {
        return new MessageDigestFactoryImpl();
    }

    @Provides
    StringObfuscator provideStringObfuscator(final MessageDigestFactory factory) {
        return new StringObfuscatorImpl(factory.getSha1MessageDigest());
    }

    @Provides
    @Singleton
    @AndroidKeyStore
    SecureKeystore provideAndroidSecureKeystore(@AndroidKeyStore final KeyStore keyStore) {
        return new SecureKeystoreImpl(keyStore);
    }

    @Provides
    @AndroidKeyStore
    KeyStore provideAndroidKeyStore() {
        try {
            return KeyStore.getInstance(ANDROID_KEYSTORE);

        } catch (final KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    SymmetricKeyProviderStorage provideSymmetricKeyProviderStorage(@ForApplication final Context context,
                                                                   final StringObfuscator stringObfuscator) {

        return SymmetricKeyProviderStorageImpl.create(context, stringObfuscator);
    }

    @Provides
    @Singleton
    SymmetricKeyProvider provideSymmetricKeyProvider(final DeviceInformation deviceInformation,
                                                            @ForApplication final Context context,
                                                            final CurrentTimeProvider currentTimeProvider,
                                                            @AndroidKeyStore final KeyStore keyStore,
                                                            final SymmetricKeyProviderStorage symmetricKeyProviderStorage) {

        if (deviceInformation.isMarshmallowOrHigher()) {
            return new SymmetricKeyProviderV23Impl(keyStore);

        } else {
            return new SymmetricKeyProviderV19Impl(context, currentTimeProvider, keyStore, symmetricKeyProviderStorage);
        }
    }

    public interface Exposes {

        @AndroidKeyStore
        SecureKeystore androidSecureKeystore();
    }

    @Qualifier
    public @interface AndroidKeyStore { }
}

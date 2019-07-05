package com.android.template.injection.application.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.template.injection.qualifier.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.storage.ApplicationStorageImpl;
import template.android.com.device.storage.SystemStorageImpl;
import template.android.com.domain.crypto.engine.CryptoEngine;
import template.android.com.domain.delegate.ApplicationStorageDelegate;
import template.android.com.domain.delegate.ApplicationStorageDelegateImpl;
import template.android.com.domain.storage.ApplicationStorage;
import template.android.com.domain.storage.SystemStorage;
import template.android.com.domain.utils.string.StringUtils;

@Module
public final class StorageModule {

    @Provides
    @Singleton
    SystemStorage provideSystemStorage(@ForApplication final Context context) {
        return new SystemStorageImpl(context);
    }

    @Provides
    @Singleton
    ApplicationStorage provideApplicationStorage(@ForApplication final Context context, final CryptoEngine cryptoEngine) {
        return ApplicationStorageImpl.create(context, cryptoEngine);
    }

    @Provides
    @Singleton
    ApplicationStorageDelegate provideApplicationStorageDelegate(final ApplicationStorage applicationStorage, final StringUtils stringUtils) {
        return new ApplicationStorageDelegateImpl(applicationStorage, stringUtils);
    }

    public interface Exposes {

        SystemStorage systemStorage();
    }
}
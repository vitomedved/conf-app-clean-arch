package com.android.template.injection.application.module;

import android.content.Context;

import com.android.template.injection.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.storage.SystemStorageImpl;
import template.android.com.domain.storage.SystemStorage;

@Module
public final class StorageModule {

    @Provides
    @Singleton
    SystemStorage provideSystemStorage(@ForApplication final Context context) {
        return new SystemStorageImpl(context);
    }

    public interface Exposes {

        SystemStorage systemStorage();
    }
}
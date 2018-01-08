package com.android.template.injection.application.module;

import android.content.Context;

import com.android.template.injection.ForApplication;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.prefs.ExamplePrefs;
import template.android.com.data.prefs.ExamplePrefsImpl;
import template.android.com.device.time.CurrentTimeProviderImpl;
import template.android.com.domain.utils.time.CurrentTimeProvider;

@Module
public final class DataModule {

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    CurrentTimeProvider provideCurrentTimeProvider() {
        return new CurrentTimeProviderImpl();
    }

    @Provides
    @Singleton
    ExamplePrefs provideExamplePrefs(@ForApplication final Context context) {
        return ExamplePrefsImpl.create(context);
    }

    public interface Exposes {

        CurrentTimeProvider currentTimeProvider();
    }
}

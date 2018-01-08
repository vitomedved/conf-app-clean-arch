package com.android.template.injection.application.module;

import android.content.Context;
import android.content.res.Resources;

import com.android.template.application.ExampleApplication;
import com.android.template.injection.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final ExampleApplication application;

    public ApplicationModule(final ExampleApplication application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    public interface Exposes {

        @ForApplication
        Context context();

        Resources resources();
    }
}

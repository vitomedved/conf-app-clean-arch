package com.android.template.application;

import android.app.Application;
import android.content.Context;

import com.android.template.BuildConfig;
import com.android.template.injection.ComponentFactory;
import com.android.template.injection.application.ApplicationComponent;
import com.android.template.injection.user.UserComponent;
import com.facebook.stetho.Stetho;

public final class ExampleApplication extends Application {

    private ApplicationComponent applicationComponent;
    private UserComponent userComponent;

    public static ExampleApplication from(final Context context) {
        return (ExampleApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        initUserComponent();
        injectMe();
        initStetho();
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    private void initUserComponent() {
        userComponent = ComponentFactory.createUserComponent(applicationComponent);
    }

    private void injectMe() {
        applicationComponent.inject(this);
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}

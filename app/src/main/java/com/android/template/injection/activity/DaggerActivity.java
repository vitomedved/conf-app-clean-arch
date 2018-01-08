package com.android.template.injection.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.template.application.ExampleApplication;
import com.android.template.injection.ComponentFactory;

public abstract class DaggerActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getActivityComponent());
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getGpsTrackingApplication());
        }

        return activityComponent;
    }

    private ExampleApplication getGpsTrackingApplication() {
        return ExampleApplication.from(this);
    }

    protected abstract void inject(final ActivityComponent component);
}


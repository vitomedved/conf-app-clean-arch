package com.android.template.injection.activity;

import com.android.template.injection.activity.module.ActivityModule;
import com.android.template.injection.activity.module.ActivityPresenterModule;
import com.android.template.injection.activity.module.UiAdapterModule;
import com.android.template.injection.scope.ActivityScope;
import com.android.template.injection.user.UserComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {
                UserComponent.class
        },
        modules = {
                ActivityModule.class,
                ActivityPresenterModule.class,
                UiAdapterModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects,
                                           ActivityComponentExposes {}

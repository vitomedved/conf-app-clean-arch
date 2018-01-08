package com.android.template.injection.user;

import com.android.template.injection.application.ApplicationComponent;
import com.android.template.injection.scope.UserScope;
import com.android.template.injection.user.module.ServiceModule;
import com.android.template.injection.user.module.UserApiClientModule;
import com.android.template.injection.user.module.UserRepositoryModule;
import com.android.template.injection.user.module.UserUseCaseModule;

import dagger.Component;

@UserScope
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ServiceModule.class,
                UserUseCaseModule.class,
                UserApiClientModule.class,
                UserRepositoryModule.class
        }
)
public interface UserComponent extends UserComponentExposes,
                                       UserComponentInjects { }

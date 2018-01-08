package com.android.template.injection.application;

import com.android.template.injection.application.module.AndroidSystemModule;
import com.android.template.injection.application.module.ApiModule;
import com.android.template.injection.application.module.ApplicationModule;
import com.android.template.injection.application.module.ConnectivityModule;
import com.android.template.injection.application.module.DataModule;
import com.android.template.injection.application.module.DbModule;
import com.android.template.injection.application.module.RepositoryModule;
import com.android.template.injection.application.module.ThreadingModule;
import com.android.template.injection.application.module.UseCaseModule;
import com.android.template.injection.application.module.UtilsModule;
import com.android.template.injection.user.module.UserRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                AndroidSystemModule.class,
                ApiModule.class,
                ConnectivityModule.class,
                DbModule.class,
                DataModule.class,
                UserRepositoryModule.class,
                ThreadingModule.class,
                RepositoryModule.class,
                UseCaseModule.class,
                UtilsModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects,
                                              ApplicationComponentExposes { }

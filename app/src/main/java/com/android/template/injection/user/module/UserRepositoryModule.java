package com.android.template.injection.user.module;

import com.android.template.injection.scope.UserScope;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.dao.ExampleDao;
import template.android.com.data.network.client.ExampleClient;
import template.android.com.data.repository.ExampleRepositoryImpl;
import template.android.com.domain.repository.ExampleRepository;

@Module
public final class UserRepositoryModule {

    @Provides
    @UserScope
    ExampleRepository provideExampleRepository(final ExampleClient exampleClient, final ExampleDao exampleDao) {
        return new ExampleRepositoryImpl(exampleClient, exampleDao);
    }

    public interface Exposes {

    }
}

package com.android.template.injection.user.module;

import com.android.template.injection.scope.UserScope;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.network.client.ExampleClient;
import template.android.com.data.network.client.ExampleClientImpl;
import template.android.com.data.network.converter.ApiMapper;
import template.android.com.data.network.service.ExampleApiService;

@Module
public final class UserApiClientModule {

    @Provides
    @UserScope
    ExampleClient provideExampleClient(final ApiMapper apiMapper, final ExampleApiService apiService) {
        return new ExampleClientImpl(apiMapper, apiService);
    }

    public interface Exposes {

    }
}

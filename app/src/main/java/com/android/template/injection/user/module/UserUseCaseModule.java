package com.android.template.injection.user.module;

import com.android.template.injection.scope.UserScope;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.repository.ExampleRepository;
import template.android.com.domain.usecase.GetExampleUseCase;
import template.android.com.domain.usecase.GetExampleUseCaseImpl;

@Module
public final class UserUseCaseModule {

    @Provides
    @UserScope
    GetExampleUseCase provideGetExampleUseCase(final ExampleRepository exampleRepository) {
        return new GetExampleUseCaseImpl(exampleRepository);
    }

    public interface Exposes {

        GetExampleUseCase getExampleUseCase();
    }
}

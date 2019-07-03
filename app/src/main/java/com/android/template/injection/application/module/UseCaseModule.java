package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.usecase.SetConferenceIdUseCase;
import template.android.com.domain.usecase.SetConferenceIdUseCaseImpl;

@Module
public final class UseCaseModule {

    @Provides
    SetConferenceIdUseCase provideSetConferenceIdUseCase() {
        return new SetConferenceIdUseCaseImpl();
    }

    public interface Exposes {
        SetConferenceIdUseCase setConferenceIdUseCase();
    }
}

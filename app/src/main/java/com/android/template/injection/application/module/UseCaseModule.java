package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.usecase.DoesConferenceExistUseCase;
import template.android.com.domain.usecase.DoesConferenceExistUseCaseImpl;

@Module
public final class UseCaseModule {

    @Provides
    DoesConferenceExistUseCase provideSetConferenceIdUseCase(final ConferenceRepository conferenceRepository) {
        return new DoesConferenceExistUseCaseImpl(conferenceRepository);
    }

    public interface Exposes {
        DoesConferenceExistUseCase doesConferenceExistUseCase();
    }
}

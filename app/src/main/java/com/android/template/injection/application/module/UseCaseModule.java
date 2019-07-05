package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.delegate.ApplicationStorageDelegate;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.usecase.conference.DoesConferenceExistUseCase;
import template.android.com.domain.usecase.conference.DoesConferenceExistUseCaseImpl;
import template.android.com.domain.usecase.conference.GetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.GetInitialConferenceIdUseCaseImpl;
import template.android.com.domain.usecase.conference.SetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.SetInitialConferenceIdUseCaseImpl;

@Module
public final class UseCaseModule {

    @Provides
    DoesConferenceExistUseCase provideSetConferenceIdUseCase(final ConferenceRepository conferenceRepository) {
        return new DoesConferenceExistUseCaseImpl(conferenceRepository);
    }

    @Provides
    SetInitialConferenceIdUseCase provideSetInitialConferenceIdUseCase(final ApplicationStorageDelegate applicationStorageDelegate) {
        return new SetInitialConferenceIdUseCaseImpl(applicationStorageDelegate);
    }

    @Provides
    GetInitialConferenceIdUseCase provideGetInitialConferenceIdUseCase(final ApplicationStorageDelegate applicationStorageDelegate) {
        return new GetInitialConferenceIdUseCaseImpl(applicationStorageDelegate);
    }

    public interface Exposes {
        DoesConferenceExistUseCase doesConferenceExistUseCase();

        SetInitialConferenceIdUseCase setInitialConferenceIdUseCase();

        GetInitialConferenceIdUseCase getInitialConferenceIdUseCase();
    }
}

package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.delegate.ApplicationStorageDelegate;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.repository.EventRepository;
import template.android.com.domain.repository.UserRepository;
import template.android.com.domain.usecase.event.GetEventInfoListByConferenceIdUseCase;
import template.android.com.domain.usecase.event.GetEventInfoListByConferenceIdUseCaseImpl;
import template.android.com.domain.usecase.conference.data.GetConferenceDataUseCase;
import template.android.com.domain.usecase.conference.data.GetConferenceDataUseCaseImpl;
import template.android.com.domain.usecase.conference.existence.DoesConferenceExistUseCase;
import template.android.com.domain.usecase.conference.existence.DoesConferenceExistUseCaseImpl;
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCaseImpl;
import template.android.com.domain.usecase.conference.initial.SetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.initial.SetInitialConferenceIdUseCaseImpl;
import template.android.com.domain.usecase.user.authentication.IsUserSignedInUseCase;
import template.android.com.domain.usecase.user.authentication.IsUserSignedInUseCaseImpl;
import template.android.com.domain.usecase.user.authentication.SignOutUseCase;
import template.android.com.domain.usecase.user.authentication.SignOutUseCaseImpl;
import template.android.com.domain.usecase.user.data.GetCurrentUserUseCase;
import template.android.com.domain.usecase.user.data.GetCurrentUserUseCaseImpl;

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

    @Provides
    IsUserSignedInUseCase provideIsUserSignedInUseCase(final UserRepository userRepository) {
        return new IsUserSignedInUseCaseImpl(userRepository);
    }

    @Provides
    GetCurrentUserUseCase provideGetCurrentUserUseCase(final UserRepository userRepository) {
        return new GetCurrentUserUseCaseImpl(userRepository);
    }

    @Provides
    SignOutUseCase provideSignOutUseCase(final UserRepository userRepository) {
        return new SignOutUseCaseImpl(userRepository);
    }

    @Provides
    GetConferenceDataUseCase provideGetConferenceDataUseCase(final ConferenceRepository conferenceRepository) {
        return new GetConferenceDataUseCaseImpl(conferenceRepository);
    }

    @Provides
    GetEventInfoListByConferenceIdUseCase provideGetEventsByConferenceIdUseCase(final EventRepository eventRepository) {
        return new GetEventInfoListByConferenceIdUseCaseImpl(eventRepository);
    }

    public interface Exposes {
        DoesConferenceExistUseCase doesConferenceExistUseCase();

        SetInitialConferenceIdUseCase setInitialConferenceIdUseCase();

        GetInitialConferenceIdUseCase getInitialConferenceIdUseCase();

        IsUserSignedInUseCase isUserSignedInUseCase();

        GetCurrentUserUseCase getCurrentUserUseCase();

        SignOutUseCase signOutUseCase();

        GetConferenceDataUseCase getConferenceDataUseCase();

        GetEventInfoListByConferenceIdUseCase getEventsByConferenceIdUseCase();
    }
}

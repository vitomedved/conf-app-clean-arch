package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.delegate.ApplicationStorageDelegate;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.repository.UserRepository;
import template.android.com.domain.usecase.conference.DoesConferenceExistUseCase;
import template.android.com.domain.usecase.conference.DoesConferenceExistUseCaseImpl;
import template.android.com.domain.usecase.conference.GetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.GetInitialConferenceIdUseCaseImpl;
import template.android.com.domain.usecase.conference.SetInitialConferenceIdUseCase;
import template.android.com.domain.usecase.conference.SetInitialConferenceIdUseCaseImpl;
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

    public interface Exposes {
        DoesConferenceExistUseCase doesConferenceExistUseCase();

        SetInitialConferenceIdUseCase setInitialConferenceIdUseCase();

        GetInitialConferenceIdUseCase getInitialConferenceIdUseCase();

        IsUserSignedInUseCase isUserSignedInUseCase();

        GetCurrentUserUseCase getCurrentUserUseCase();

        SignOutUseCase signOutUseCase();
    }
}

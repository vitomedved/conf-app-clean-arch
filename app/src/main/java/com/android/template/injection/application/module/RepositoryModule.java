package com.android.template.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.firebase.mapper.FirebaseMapper;
import template.android.com.data.firebase.mapper.FirebaseMapperImpl;
import template.android.com.data.repository.ConferenceRepositoryImpl;
import template.android.com.data.repository.UserRepositoryImpl;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.repository.UserRepository;
import template.android.com.domain.utils.string.StringUtils;

@Module
public final class RepositoryModule {

    @Provides
    ConferenceRepository provideConferenceRepository(final FirebaseMapper firebaseMapper) {
        return new ConferenceRepositoryImpl(firebaseMapper);
    }

    @Provides
    UserRepository provideUserRepository(final StringUtils stringUtils) {
        return new UserRepositoryImpl(stringUtils);
    }

    @Provides
    @Singleton
    FirebaseMapper provideFirebaseMapper() {
        return new FirebaseMapperImpl();
    }

    public interface Exposes {

    }
}

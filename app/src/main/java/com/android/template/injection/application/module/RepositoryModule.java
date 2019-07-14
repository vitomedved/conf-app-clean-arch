package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.repository.ConferenceRepositoryImpl;
import template.android.com.data.repository.EventRepositoryImpl;
import template.android.com.data.repository.UserRepositoryImpl;
import template.android.com.domain.repository.ConferenceRepository;
import template.android.com.domain.repository.EventRepository;
import template.android.com.domain.repository.UserRepository;
import template.android.com.domain.utils.string.StringUtils;

@Module
public final class RepositoryModule {

    @Provides
    ConferenceRepository provideConferenceRepository() {
        return new ConferenceRepositoryImpl();
    }

    @Provides
    UserRepository provideUserRepository(final StringUtils stringUtils) {
        return new UserRepositoryImpl(stringUtils);
    }

    @Provides
    EventRepository provideEventRepository() {
        return new EventRepositoryImpl();
    }

    public interface Exposes {

    }
}

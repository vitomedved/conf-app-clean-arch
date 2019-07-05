package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.repository.ConferenceRepositoryImpl;
import template.android.com.domain.repository.ConferenceRepository;

@Module
public final class RepositoryModule {

    @Provides
    ConferenceRepository provideConferenceRepository() {
        return new ConferenceRepositoryImpl();
    }

    public interface Exposes {

    }
}

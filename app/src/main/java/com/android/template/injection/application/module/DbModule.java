package com.android.template.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.data.dao.DbMapper;
import template.android.com.data.dao.DbMapperImpl;
import template.android.com.data.dao.ExampleDao;
import template.android.com.data.dao.ExampleDaoImpl;
import template.android.com.domain.utils.StringUtils;

@Module
public final class DbModule {

    @Provides
    @Singleton
    ExampleDao provideExampleDao() {
        return new ExampleDaoImpl();
    }

    @Provides
    @Singleton
    DbMapper provideDbMapper(final StringUtils stringUtils) {
        return new DbMapperImpl(stringUtils);
    }

    public interface Exposes {

        ExampleDao exampleDao();
    }
}

package com.android.template.injection.application.module;

import com.android.template.ui.ViewModelConverter;
import com.android.template.ui.ViewModelConverterImpl;
import com.android.template.utils.view.ViewUtils;
import com.android.template.utils.view.ViewUtilsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.domain.utils.DateUtils;
import template.android.com.domain.utils.DateUtilsImpl;
import template.android.com.domain.utils.ListUtils;
import template.android.com.domain.utils.ListUtilsImpl;
import template.android.com.domain.utils.StringUtils;
import template.android.com.domain.utils.StringUtilsImpl;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    DateUtils provideDateUtils(final StringUtils stringUtils) {
        return new DateUtilsImpl(stringUtils);
    }

    @Provides
    @Singleton
    ListUtils provideListUtils() {
        return new ListUtilsImpl();
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtilsImpl();
    }

    @Provides
    @Singleton
    ViewModelConverter provideViewModelConverter(final DateUtils dateUtils, final StringUtils stringUtils) {
        return new ViewModelConverterImpl(dateUtils, stringUtils);
    }

    @Provides
    @Singleton
    ViewUtils provideViewUtils() {
        return new ViewUtilsImpl();
    }

    public interface Exposes {

        DateUtils dateUtils();

        ListUtils listUtils();

        StringUtils stringUtils();

        ViewModelConverter viewModelConverter();

        ViewUtils viewUtils();
    }
}

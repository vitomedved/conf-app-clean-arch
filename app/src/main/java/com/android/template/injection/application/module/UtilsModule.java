package com.android.template.injection.application.module;

import android.content.Context;

import com.android.template.injection.qualifier.ForApplication;
import com.android.template.ui.ViewModelConverter;
import com.android.template.ui.ViewModelConverterImpl;
import com.android.template.utils.StethoInitializer;
import com.android.template.utils.StethoInitializerImpl;
import com.android.template.utils.view.ViewUtils;
import com.android.template.utils.view.ViewUtilsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.util.byteformat.AndroidBase64ToolImpl;
import template.android.com.domain.utils.byteformat.Base64Tool;
import template.android.com.domain.utils.collection.CollectionUtils;
import template.android.com.domain.utils.collection.CollectionUtilsImpl;
import template.android.com.domain.utils.collection.ListUtils;
import template.android.com.domain.utils.collection.ListUtilsImpl;
import template.android.com.domain.utils.date.DateUtils;
import template.android.com.domain.utils.date.DateUtilsImpl;
import template.android.com.domain.utils.string.StringUtils;
import template.android.com.domain.utils.string.StringUtilsImpl;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    Base64Tool provideBase64Tool() {
        return new AndroidBase64ToolImpl();
    }

    @Provides
    @Singleton
    DateUtils provideDateUtils(final StringUtils stringUtils) {
        return new DateUtilsImpl(stringUtils);
    }

    @Provides
    @Singleton
    CollectionUtils provideCollectionUtils() {
        return new CollectionUtilsImpl();
    }

    @Provides
    @Singleton
    ListUtils provideListUtils(final CollectionUtils collectionUtils) {
        return new ListUtilsImpl(collectionUtils);
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
    ViewUtils provideViewUtils(@ForApplication final Context context) {
        return new ViewUtilsImpl(context);
    }

    @Provides
    @Singleton
    StethoInitializer provideStethoInitializer(@ForApplication final Context context) {
        return new StethoInitializerImpl(context);
    }

    public interface Exposes {

        Base64Tool base64Tool();

        DateUtils dateUtils();

        CollectionUtils collectionUtils();

        ListUtils listUtils();

        StringUtils stringUtils();

        ViewModelConverter viewModelConverter();

        ViewUtils viewUtils();
    }
}

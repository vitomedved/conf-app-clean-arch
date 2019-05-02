package com.android.template.utils.application;

import com.android.template.BuildConfig;

import template.android.com.domain.application.ApplicationInformation;

public final class ApplicationInformationImpl implements ApplicationInformation {

    @Override
    public int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    @Override
    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getVersionDate() {
        return BuildConfig.VERSION_DATE;
    }
}
/**/
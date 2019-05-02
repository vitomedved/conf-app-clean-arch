package com.android.template.injection.application.module;

import android.content.ClipboardManager;
import android.content.Context;

import com.android.template.injection.qualifier.ForApplication;
import com.android.template.utils.application.ApplicationInformationImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.device.ClipboardUtilImpl;
import template.android.com.device.device.DeviceInformationImpl;
import template.android.com.device.device.PermissionInformationImpl;
import template.android.com.domain.application.ApplicationInformation;
import template.android.com.domain.device.ClipboardUtil;
import template.android.com.domain.device.DeviceInformation;
import template.android.com.domain.device.PermissionInformation;

@Module
public final class DeviceModule {

    @Provides
    @Singleton
    ApplicationInformation provideApplicationInformation() {
        return new ApplicationInformationImpl();
    }

    @Provides
    @Singleton
    ClipboardUtil provideClipboardUtil(final ClipboardManager clipboardManager) {
        return new ClipboardUtilImpl(clipboardManager);
    }

    @Provides
    @Singleton
    DeviceInformation provideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    @Provides
    @Singleton
    PermissionInformation providePermissionInformation(@ForApplication final Context context) {
        return new PermissionInformationImpl(context);
    }

    public interface Exposes {

        ApplicationInformation applicationInformation();

        ClipboardUtil clipboardUtil();

        DeviceInformation deviceInformation();

        PermissionInformation permissionInformation();
    }
}

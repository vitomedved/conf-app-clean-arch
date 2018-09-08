package com.android.template.injection.application.module;

import dagger.Module;
import dagger.Provides;
import template.android.com.device.device.DeviceInformationImpl;
import template.android.com.domain.device.DeviceInformation;

@Module
public final class DeviceModule {

    @Provides
    DeviceInformation provideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    public interface Exposes {

        DeviceInformation deviceInformation();
    }
}

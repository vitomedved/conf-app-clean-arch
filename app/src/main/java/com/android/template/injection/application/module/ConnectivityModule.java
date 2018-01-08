package com.android.template.injection.application.module;

import android.content.Context;

import com.android.template.injection.ForApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import template.android.com.device.connectivity.ConnectivityManagerWrapper;
import template.android.com.device.connectivity.ConnectivityManagerWrapperImpl;
import template.android.com.device.connectivity.ConnectivityReceiver;
import template.android.com.device.connectivity.ConnectivityReceiverImpl;
import template.android.com.device.connectivity.NetworkUtils;
import template.android.com.device.connectivity.NetworkUtilsImpl;

@Module
public final class ConnectivityModule {

    @Provides
    @Singleton
    ConnectivityManagerWrapper provideConnectivityManagerWrapper(@ForApplication final Context context) {
        return new ConnectivityManagerWrapperImpl(context);
    }

    @Provides
    @Singleton
    ConnectivityReceiver provideConnectivityReceiver(@ForApplication final Context context, final NetworkUtils networkUtils,
                                                     @Named(ThreadingModule.BACKGROUND_SCHEDULER) final Scheduler backgroundScheduler) {

        return new ConnectivityReceiverImpl(context, networkUtils, backgroundScheduler);
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(final ConnectivityManagerWrapper connectivityManagerWrapper) {
        return new NetworkUtilsImpl(connectivityManagerWrapper);
    }

    public interface Exposes {

        ConnectivityReceiver provideConnectivityReceiver();
    }
}

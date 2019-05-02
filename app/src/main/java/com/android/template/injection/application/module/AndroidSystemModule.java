package com.android.template.injection.application.module;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.android.template.injection.qualifier.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AndroidSystemModule {

    @Provides
    @Singleton
    BluetoothManager provideBluetoothManager(@ForApplication final Context context) {
        return BluetoothManager.class.cast(context.getSystemService(Context.BLUETOOTH_SERVICE));
    }

    @Provides
    @Singleton
    ClipboardManager provideClipboardManager(@ForApplication final Context context) {
        return ClipboardManager.class.cast(context.getSystemService(Context.CLIPBOARD_SERVICE));
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(@ForApplication final Context context) {
        return ConnectivityManager.class.cast(context.getSystemService(Context.CONNECTIVITY_SERVICE));
    }

    @Provides
    @Singleton
    ContentResolver provideContentResolver(@ForApplication final Context context) {
        return context.getContentResolver();
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager(@ForApplication final Context context) {
        return LocationManager.class.cast(context.getSystemService(Context.LOCATION_SERVICE));
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(@ForApplication final Context context) {
        return NotificationManager.class.cast(context.getSystemService(Context.NOTIFICATION_SERVICE));
    }

    @Provides
    @Singleton
    PackageManager providePackageManager(@ForApplication final Context context) {
        return context.getPackageManager();
    }

    @Provides
    @Singleton
    TelephonyManager provideTelephonyManager(@ForApplication final Context context) {
        return TelephonyManager.class.cast(context.getSystemService(Context.TELEPHONY_SERVICE));
    }

    @SuppressLint("WifiManagerPotentialLeak")
    @Provides
    @Singleton
    WifiManager provideWifiManager(@ForApplication final Context context) {
        return WifiManager.class.cast(context.getSystemService(Context.WIFI_SERVICE));
    }

    public interface Exposes {

        BluetoothManager bluetoothManager();

        ClipboardManager clipboardManager();

        ConnectivityManager connectivityManager();

        LocationManager locationManager();

        NotificationManager notificationManager();

        PackageManager packageManager();

        TelephonyManager telephonyManager();

        WifiManager wifiManager();
    }
}

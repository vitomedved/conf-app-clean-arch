package com.android.template.injection.application.module;

import android.app.NotificationManager;
import android.content.Context;

import com.android.template.injection.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AndroidSystemModule {

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(@ForApplication final Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public interface Exposes {

        NotificationManager notificationManager();
    }
}

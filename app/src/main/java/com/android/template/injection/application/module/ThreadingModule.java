package com.android.template.injection.application.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import template.android.com.device.executor.ScheduledExecutorImpl;
import template.android.com.domain.executor.ScheduledExecutor;

@Module
public final class ThreadingModule {

    public static final String MAIN_SCHEDULER = "main_thread_scheduler";
    public static final String BACKGROUND_SCHEDULER = "background_scheduler";
    public static final String COMPUTATION_SCHEDULER = "computation_scheduler";

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(COMPUTATION_SCHEDULER)
    Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Singleton
    ScheduledExecutor provideScheduledExecutor() {
        return new ScheduledExecutorImpl();
    }

    public interface Exposes {

        @Named(MAIN_SCHEDULER)
        Scheduler mainThreadScheduler();

        @Named(BACKGROUND_SCHEDULER)
        Scheduler backgroundScheduler();

        ScheduledExecutor scheduledExecutor();
    }
}

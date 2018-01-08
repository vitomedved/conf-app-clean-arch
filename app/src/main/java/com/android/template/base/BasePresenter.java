package com.android.template.base;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.android.template.injection.application.module.ThreadingModule;
import com.android.template.ui.Router;
import com.android.template.utils.ViewActionQueue;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.operators.OperatorOnBackpressureBuffer;
import rx.subscriptions.CompositeSubscription;
import template.android.com.device.connectivity.ConnectivityReceiver;

public abstract class BasePresenter<View extends BaseView> implements ScopedPresenter {

    @Inject
    protected ConnectivityReceiver connectivityReceiver;

    @Inject
    protected Router router;

    @Inject
    @Named(ThreadingModule.MAIN_SCHEDULER)
    protected Scheduler mainThreadScheduler;

    @Inject
    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    protected Scheduler backgroundScheduler;

    private final WeakReference<View> viewReference;

    private Subscription viewActionsSubscription;

    protected final ViewActionQueue<View> viewActionQueue = new ViewActionQueue<>();

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final CompositeSubscription permissionSubscriptions = new CompositeSubscription();

    public BasePresenter(final View view) {
        viewReference = new WeakReference<>(view);
    }

    @Override
    public void start() {

    }

    @Override
    @CallSuper
    public void activate() {
        viewActionsSubscription = viewActionQueue.viewActionsObservable()
                                                 .lift(getViewActionBackPressureStrategy())
                                                 .observeOn(mainThreadScheduler)
                                                 .subscribe(this::doIfViewNotNull);
        viewActionQueue.resume();

        subscribeToConnectivityChange();
    }

    protected Observable.Operator<Action1<View>, Action1<View>> getViewActionBackPressureStrategy() {
        return OperatorOnBackpressureBuffer.instance();
    }

    protected final void onViewAction(final Action1<View> viewAction) {
        viewActionQueue.scheduleViewAction(viewAction);
    }

    private void subscribeToConnectivityChange() {
        addSubscription(connectivityReceiver.getConnectivityStatus()
                                            .subscribeOn(backgroundScheduler)
                                            .observeOn(mainThreadScheduler)
                                            .subscribe(this::onConnectivityChange, this::logError));
    }

    protected void onConnectivityChange(final boolean isConnected) {
        // Template method
    }

    @Override
    @CallSuper
    public void deactivate() {
        viewActionQueue.pause();
        viewActionsSubscription.unsubscribe();
        subscriptions.clear();
    }

    @Override
    public void stop() {
        permissionSubscriptions.clear();
    }

    @Override
    @CallSuper
    public void destroy() {
        viewActionQueue.destroy();
        subscriptions.clear();
    }

    @Override
    public void back() {
        router.goBack();
    }

    protected void addSubscription(final Subscription subscription) {
        subscriptions.add(subscription);
    }

    protected void addPermissionSubscription(final Subscription subscription) {
        permissionSubscriptions.add(subscription);
    }

    protected final void doIfConnectedToInternet(final Action0 ifConnected, final Action0 ifNotConnected) {
        addSubscription(connectivityReceiver.isConnected()
                                            .subscribeOn(backgroundScheduler)
                                            .observeOn(mainThreadScheduler)
                                            .subscribe(isConnected -> onConnectedToInternet(isConnected, ifConnected, ifNotConnected),
                                                       this::logError));
    }

    protected final void observeInternetConnection(final Action0 ifConnected, final Action0 ifNotConnected) {
        addSubscription(connectivityReceiver.getConnectivityStatus()
                                            .distinctUntilChanged()
                                            .subscribeOn(backgroundScheduler)
                                            .observeOn(mainThreadScheduler)
                                            .subscribe(isConnected -> onConnectedToInternet(isConnected, ifConnected, ifNotConnected),
                                                       this::logError));
    }

    private void onConnectedToInternet(final boolean isConnected, final Action0 ifConnected, final Action0 ifNotConnected) {
        (isConnected ? ifConnected : ifNotConnected).call();
    }

    public final void logError(final Throwable throwable) {
        if (!TextUtils.isEmpty(throwable.getMessage())) {
            Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
        }
    }

    protected void doIfViewNotNull(final Action1<View> whenViewNotNull) {
        final View view = getNullableView();
        if (view != null) {
            whenViewNotNull.call(view);
        }
    }

    protected <R> R getIfViewNotNull(final Func1<View, R> whenViewNotNull, final R defaultValue) {
        final View view = getNullableView();
        if (view != null) {
            return whenViewNotNull.call(view);
        }
        return defaultValue;
    }

    @Nullable
    protected View getNullableView() {
        return viewReference.get();
    }
}


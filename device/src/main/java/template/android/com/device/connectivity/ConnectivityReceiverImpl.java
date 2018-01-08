package template.android.com.device.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.subjects.PublishSubject;

public final class ConnectivityReceiverImpl extends BroadcastReceiver implements ConnectivityReceiver {

    private static final String TAG = ConnectivityReceiverImpl.class.getSimpleName();

    private static final String ACTION_CONNECTIVITY_CHANGE = ConnectivityManager.CONNECTIVITY_ACTION;

    private final NetworkUtils networkUtils;
    private final Scheduler backgroundScheduler;
    private final PublishSubject<Boolean> subject;

    public ConnectivityReceiverImpl(final Context context, final NetworkUtils networkUtils, final Scheduler backgroundScheduler) {
        this.networkUtils = networkUtils;
        this.backgroundScheduler = backgroundScheduler;
        this.subject = PublishSubject.create();

        registerReceiver(context);
    }

    private void registerReceiver(final Context context) {
        context.registerReceiver(this, new IntentFilter(ACTION_CONNECTIVITY_CHANGE));
    }

    @Override
    public Observable<Boolean> getConnectivityStatus() {
        return subject.distinctUntilChanged()
                      .subscribeOn(backgroundScheduler)
                      .observeOn(backgroundScheduler);
    }

    @Override
    public Single<Boolean> isConnected() {
        return networkUtils.isConnectedToInternet();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (subject == null) {
            return;
        }

        checkConnectionStatus();
    }

    private void checkConnectionStatus() {
        networkUtils.isConnectedToInternet()
                    .subscribeOn(backgroundScheduler)
                    .observeOn(backgroundScheduler)
                    .subscribe(this::onNetworkStatus, this::onNetworkStatusError);
    }

    private void onNetworkStatus(final Boolean isConnected) {
        subject.onNext(isConnected);
    }

    private void onNetworkStatusError(final Throwable throwable) {
        // No-op
    }
}
package template.android.com.device.connectivity;


import rx.Observable;
import rx.Single;

public interface ConnectivityReceiver {

    Observable<Boolean> getConnectivityStatus();

    Single<Boolean> isConnected();
}

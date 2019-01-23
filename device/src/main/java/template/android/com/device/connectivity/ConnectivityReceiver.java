package template.android.com.device.connectivity;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ConnectivityReceiver {

    Observable<Boolean> getConnectivityStatus();

    Single<Boolean> isConnected();
}

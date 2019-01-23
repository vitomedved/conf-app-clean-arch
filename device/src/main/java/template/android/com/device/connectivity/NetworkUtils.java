package template.android.com.device.connectivity;

import io.reactivex.Single;

public interface NetworkUtils {

    Single<Boolean> isConnectedToInternet();

    Single<NetworkData> getActiveNetworkData();
}
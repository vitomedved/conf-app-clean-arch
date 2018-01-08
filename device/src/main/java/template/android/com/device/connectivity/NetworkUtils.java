package template.android.com.device.connectivity;


import rx.Single;

public interface NetworkUtils {

    Single<Boolean> isConnectedToInternet();

    Single<NetworkData> getActiveNetworkData();
}
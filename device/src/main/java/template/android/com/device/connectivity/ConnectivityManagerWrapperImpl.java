package template.android.com.device.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConnectivityManagerWrapperImpl implements ConnectivityManagerWrapper {

    private final ConnectivityManager connectivityManager;

    public ConnectivityManagerWrapperImpl(final ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean isConnectedToNetwork() {

        final NetworkInfo activeNetworkInfo = getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public NetworkData getNetworkData() {

        final NetworkInfo activeNetworkInfo = getActiveNetworkInfo();

        final boolean hasInternetConnection = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        final boolean isMobileConnection = activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;

        return new NetworkData(hasInternetConnection, isMobileConnection);
    }

    private NetworkInfo getActiveNetworkInfo() {
        return connectivityManager.getActiveNetworkInfo();
    }
}

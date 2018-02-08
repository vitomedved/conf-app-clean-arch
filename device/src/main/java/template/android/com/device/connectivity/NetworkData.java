package template.android.com.device.connectivity;

public final class NetworkData {

    public final boolean hasInternetConnection;
    public final boolean isMobileConnection;

    public NetworkData(final boolean hasInternetConnection, final boolean isMobileConnection) {
        this.hasInternetConnection = hasInternetConnection;
        this.isMobileConnection = isMobileConnection;
    }
}
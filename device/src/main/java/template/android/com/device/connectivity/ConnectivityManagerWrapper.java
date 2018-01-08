package template.android.com.device.connectivity;

public interface ConnectivityManagerWrapper {

    boolean isConnectedToNetwork();

    NetworkData getNetworkData();
}

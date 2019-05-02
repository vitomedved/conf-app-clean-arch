package template.android.com.device.connectivity;

import com.annimon.stream.Optional;

import java.net.InetAddress;
import java.net.UnknownHostException;

import io.reactivex.Single;

public final class NetworkUtilsImpl implements NetworkUtils {

    private static final String EMPTY = "";
    private static final String PING_ADDRESS = "www.google.com";

    private final ConnectivityManagerWrapper connectivityManagerWrapper;

    public NetworkUtilsImpl(final ConnectivityManagerWrapper connectivityManagerWrapper) {
        this.connectivityManagerWrapper = connectivityManagerWrapper;
    }

    @Override
    public Single<Boolean> isConnectedToInternet() {
        return Single.fromCallable(() -> isConnectedToNetwork() && canResolveAddress(PING_ADDRESS));
    }

    @Override
    public Single<NetworkData> getActiveNetworkData() {
        return Single.fromCallable(connectivityManagerWrapper::getNetworkData);
    }

    private boolean canResolveAddress(final String url) {
        return pingAddress(url);
    }

    private boolean isConnectedToNetwork() {
        return connectivityManagerWrapper.isConnectedToNetwork();
    }

    private boolean pingAddress(final String url) {

        try {
            return Optional.ofNullable(InetAddress.getByName(url))
                           .map(InetAddress::getHostAddress)
                           .map(value -> !EMPTY.equals(value))
                           .orElse(false);

        } catch (final UnknownHostException e) {
            return false;
        }
    }
}

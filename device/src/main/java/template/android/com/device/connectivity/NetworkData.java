package template.android.com.device.connectivity;

public final class NetworkData {

    public final boolean hasInternetConnection;
    public final boolean isMobileConnection;

    public NetworkData(final boolean hasInternetConnection, final boolean isMobileConnection) {
        this.hasInternetConnection = hasInternetConnection;
        this.isMobileConnection = isMobileConnection;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final NetworkData that = (NetworkData) o;

        if (hasInternetConnection != that.hasInternetConnection) {
            return false;
        }
        return isMobileConnection == that.isMobileConnection;
    }

    @Override
    public int hashCode() {
        int result = (hasInternetConnection ? 1 : 0);
        result = 31 * result + (isMobileConnection ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NetworkData{" +
                "hasInternetConnection=" + hasInternetConnection +
                ", isMobileConnection=" + isMobileConnection +
                '}';
    }
}

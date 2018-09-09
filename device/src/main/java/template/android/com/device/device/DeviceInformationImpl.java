package template.android.com.device.device;

import android.os.Build;

import template.android.com.domain.device.DeviceInformation;

public final class DeviceInformationImpl implements DeviceInformation{

    @Override
    public boolean isKitKatOrHigher() {
        return isCurrentVersionHigherThan(Build.VERSION_CODES.KITKAT);
    }

    @Override
    public boolean isLollipopOrHigher() {
        return isCurrentVersionHigherThan(Build.VERSION_CODES.LOLLIPOP);
    }

    @Override
    public boolean isMarshmallowOrHigher() {
        return isCurrentVersionHigherThan(Build.VERSION_CODES.M);
    }

    @Override
    public boolean isNougatOrHigher() {
        return isCurrentVersionHigherThan(Build.VERSION_CODES.N);
    }

    @Override
    public boolean isOreoOrHigher() {
        return isCurrentVersionHigherThan(Build.VERSION_CODES.O);
    }

    private boolean isCurrentVersionHigherThan(final int targetVersion) {
        return Build.VERSION.SDK_INT >= targetVersion;
    }
}

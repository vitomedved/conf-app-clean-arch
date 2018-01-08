package template.android.com.device.time;

import java.util.concurrent.TimeUnit;

import template.android.com.domain.utils.time.CurrentTimeProvider;

public final class CurrentTimeProviderImpl implements CurrentTimeProvider {

    @Override
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public long getCurrentTimestamp() {
        return TimeUnit.MILLISECONDS.toSeconds(getCurrentTimeMillis());
    }
}

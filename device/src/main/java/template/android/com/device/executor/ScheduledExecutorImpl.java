package template.android.com.device.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import template.android.com.domain.executor.ScheduledExecutor;

public final class ScheduledExecutorImpl implements ScheduledExecutor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void scheduleRunnable(final Runnable runnable, final int delay) {
        scheduleRunnable(runnable, TimeUnit.MILLISECONDS, delay);
    }

    @Override
    public void scheduleRunnable(final Runnable runnable, final TimeUnit timeUnit, final int delay) {

        Objects.requireNonNull(runnable, "Runnable == null");
        Objects.requireNonNull(runnable, "timeUnit == null");

        handler.postDelayed(runnable, timeUnit.toMillis(delay));
    }

    @Override
    public void removeFromQueue(final Runnable runnable) {
        handler.removeCallbacks(runnable);
    }
}

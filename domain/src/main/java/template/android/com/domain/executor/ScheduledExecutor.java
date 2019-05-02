package template.android.com.domain.executor;

import java.util.concurrent.TimeUnit;

public interface ScheduledExecutor {

    void scheduleRunnable(Runnable runnable, int delay);

    void scheduleRunnable(Runnable runnable, TimeUnit timeUnit, int delay);

    void removeFromQueue(Runnable runnable);
}

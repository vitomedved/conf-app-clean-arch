package template.android.com.domain.utils.function;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public final class Actions {

    private static final Action NO_OP_ACTION_0 = () -> {};
    private static final Consumer NO_OP_ACTION_1 = o -> {};
    private static final Runnable NO_OP_RUNNABLE = () -> {};

    private Actions() {
        throw new AssertionError();
    }

    public static Action noOpAction0() {
        return NO_OP_ACTION_0;
    }

    @SuppressWarnings("unchecked")
    public static <T> Consumer<T> noOpAction1() {
        return (Consumer<T>) NO_OP_ACTION_1;
    }

    public static Runnable noOpRunnable() {
        return NO_OP_RUNNABLE;
    }
}

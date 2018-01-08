package com.android.template.utils;

import rx.functions.Action0;
import rx.functions.Action1;

public final class Actions {

    private static final Action0 NO_OP_ACTION_0 = () -> {};
    private static final Action1 NO_OP_ACTION_1 = o -> {};

    private Actions() {

    }

    public static Action0 noOpAction0() {
        return NO_OP_ACTION_0;
    }

    @SuppressWarnings("unchecked")
    public static <T> Action1<T> noOpAction1() {
        return (Action1<T>) NO_OP_ACTION_1;
    }
}

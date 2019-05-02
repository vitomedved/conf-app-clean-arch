package com.android.template.utils.ui;

import android.content.Context;
import android.content.DialogInterface;

public final class AlertDialogFactoryImpl implements AlertDialogFactory {

    private static final DialogInterface.OnClickListener EMPTY = new EmptyOnClickListener();

    private final Context context;

    public AlertDialogFactoryImpl(final Context context) {
        this.context = context;
    }

    @Override
    public DialogInterface.OnClickListener adaptToRunnable(final Runnable runnable) {
        return new DialogInterfaceOnClickListenerRunnableAdapter(runnable);
    }

    @Override
    public DialogInterface.OnClickListener getEmptyListener() {
        return EMPTY;
    }

    private static final class EmptyOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            // NO-OP
        }
    }
}

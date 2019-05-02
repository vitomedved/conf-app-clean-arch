package com.android.template.utils.ui;

import android.content.DialogInterface;

public final class DialogInterfaceOnClickListenerRunnableAdapter implements DialogInterface.OnClickListener {

    private final Runnable runnable;

    public DialogInterfaceOnClickListenerRunnableAdapter(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        runnable.run();
    }
}

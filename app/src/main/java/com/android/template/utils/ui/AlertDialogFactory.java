package com.android.template.utils.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public interface AlertDialogFactory {

    DialogInterface.OnClickListener adaptToRunnable(Runnable runnable);

    DialogInterface.OnClickListener getEmptyListener();
}

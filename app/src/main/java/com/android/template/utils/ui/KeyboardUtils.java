package com.android.template.utils.ui;

import android.view.View;

public interface KeyboardUtils {

    void showSoftKeyboard();

    void hideSoftKeyboard(View v);

    boolean isKeyboardShown();
}

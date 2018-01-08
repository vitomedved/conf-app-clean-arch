package com.android.template.utils.ui;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtilsImpl implements KeyboardUtils {

    private final InputMethodManager inputMethodManager;

    public KeyboardUtilsImpl(final InputMethodManager inputMethodManager) {
        this.inputMethodManager = inputMethodManager;
    }

    @Override
    public void showSoftKeyboard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void hideSoftKeyboard(final View v) {
        if (v != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public boolean isKeyboardShown() {
        return inputMethodManager.isAcceptingText();
    }
}

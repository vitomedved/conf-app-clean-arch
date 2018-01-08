package com.android.template.ui;

public interface Router {

    void showMainScreen();

    void goBack();

    boolean showPageInExternalBrowser(String url);
}

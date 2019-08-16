package com.android.template.ui;

public interface Router {

    void showMainScreen();

    void goBack();

    boolean showPageInExternalBrowser(String url);

    void showAddInitConferenceIdScreen();

    void showAddNewConferenceScreen();

    void showHomeScreen();

    void showAboutConferenceScreen();

    void showScheduleScreen();

    void showEventActivity(String eventId);
}

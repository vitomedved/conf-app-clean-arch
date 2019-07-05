package com.android.template.ui.main;

import com.android.template.base.BasePresenter;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(final MainContract.View view) {
        super(view);
    }

    @Override
    public void showInitScreen() {
        // TODO: check if conference ID is saved in shared preferences, if it is show homeScreen, else show welcomeScreen
        router.showWelcomeScreen();
    }
}

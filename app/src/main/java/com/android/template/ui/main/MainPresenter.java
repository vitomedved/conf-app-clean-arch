package com.android.template.ui.main;

import com.android.template.base.BasePresenter;
import com.android.template.ui.Router;
import com.android.template.utils.Actions;

import javax.inject.Inject;

import template.android.com.domain.usecase.GetExampleUseCase;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(final MainContract.View view) {
        super(view);
    }

    @Override
    public void showInitScreen() {
        router.showWelcomeScreen();
    }
}

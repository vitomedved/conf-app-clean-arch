package com.android.template.ui.main;

import android.util.Log;

import com.android.template.base.BasePresenter;

import javax.inject.Inject;

import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase;
import template.android.com.domain.utils.string.StringUtils;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(final MainContract.View view) {
        super(view);
    }

    @Inject
    GetInitialConferenceIdUseCase getInitialConferenceIdUseCase;

    @Inject
    StringUtils stringUtils;

    @Override
    public void showAddInitConferenceIdScreen() {
        router.showAddInitConferenceIdScreen();
    }

    @Override
    public void trySkippingAddConferenceScreen() {
        executeGetInitialConferenceIdUseCase();
    }

    private void executeGetInitialConferenceIdUseCase() {
        addDisposable(getInitialConferenceIdUseCase.execute()
                                                   .subscribeOn(backgroundScheduler)
                                                   .observeOn(mainThreadScheduler)
                                                   .subscribe(this::processGetInitialConferenceIdUseCaseSuccess,
                                                           this::processGetInitialConferenceIdUseCaseError));
    }

    private void processGetInitialConferenceIdUseCaseSuccess(String conferenceId) {
        if (stringUtils.isEmpty(conferenceId)) {
            router.showAddInitConferenceIdScreen();
        } else {
            router.showHomeScreen();
        }
    }

    private void processGetInitialConferenceIdUseCaseError(Throwable throwable) {
        Log.e("MainPresenter", "Init conference ID could not be get: " + throwable);
    }
}
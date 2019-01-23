package com.android.template.ui.main;

import com.android.template.base.BasePresenter;
import com.android.template.utils.Actions;

import javax.inject.Inject;

import template.android.com.domain.usecase.GetExampleUseCase;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    GetExampleUseCase getExampleUseCase;

    public MainPresenter(final MainContract.View view) {
        super(view);
    }

    @Override
    public void init() {
        addDisposable(getExampleUseCase.execute()
                                       .subscribeOn(backgroundScheduler)
                                       .observeOn(mainThreadScheduler)
                                       .subscribe(Actions.noOpAction1(),
                                                    Actions.noOpAction1()));
    }
}

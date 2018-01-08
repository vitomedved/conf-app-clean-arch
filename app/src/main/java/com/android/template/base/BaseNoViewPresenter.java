package com.android.template.base;

public abstract class BaseNoViewPresenter extends BasePresenter<BaseView> {

    public BaseNoViewPresenter() {
        super(new BaseView() { });
    }
}

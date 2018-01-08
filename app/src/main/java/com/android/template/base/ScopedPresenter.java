package com.android.template.base;

public interface ScopedPresenter {

    ScopedPresenter EMPTY = NoOpScopedPresenter.INSTANCE;

    void start();

    void activate();

    void deactivate();

    void stop();

    void destroy();

    void back();
}


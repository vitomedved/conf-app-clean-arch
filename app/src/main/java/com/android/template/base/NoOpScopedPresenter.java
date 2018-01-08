package com.android.template.base;

final class NoOpScopedPresenter implements ScopedPresenter {

    static ScopedPresenter INSTANCE = new NoOpScopedPresenter();

    private NoOpScopedPresenter() {

    }

    @Override
    public void start() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void back() {

    }
}

package com.android.template.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.template.R;
import com.android.template.base.BaseActivity;
import com.android.template.base.ScopedPresenter;
import com.android.template.injection.activity.ActivityComponent;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Might change name of the method. In future, you will either be redirected to welcome fragment or some kind of initial screen.
        presenter.showInitScreen();
    }

    @Override
    protected void inject(final ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected ScopedPresenter getPresenter() {
        return presenter;
    }
}

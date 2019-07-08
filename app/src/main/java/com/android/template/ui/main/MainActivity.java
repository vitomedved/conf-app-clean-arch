package com.android.template.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.template.R;
import com.android.template.base.BaseActivity;
import com.android.template.base.ScopedPresenter;
import com.android.template.injection.activity.ActivityComponent;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity implements MainContract.View {

    public static final String IS_INIT_SCREEN_KEY = "isInitScreen";

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState) {

            boolean isInitScreen = true;

            Bundle extras = getIntent().getExtras();
            if(null != extras) {
                isInitScreen = extras.getBoolean(IS_INIT_SCREEN_KEY, false);
            }

            if (isInitScreen) {
                presenter.trySkippingAddConferenceScreen();
            } else {
                presenter.showAddInitConferenceIdScreen();
            }
        }
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

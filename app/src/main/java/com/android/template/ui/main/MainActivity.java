package com.android.template.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.template.R;
import com.android.template.base.BaseActivity;
import com.android.template.base.ScopedPresenter;
import com.android.template.injection.activity.ActivityComponent;

import javax.inject.Inject;

import template.android.com.data.DataJavaInvoker;
import template.android.com.device.DeviceJavaInvoker;
import template.android.com.domain.DomainJavaInvoker;

public final class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To ensure all modules are well connected.
        new DataJavaInvoker().invoke();
        new DeviceJavaInvoker().invoke();
        new DomainJavaInvoker().invoke();

        presenter.init();
    }

    @Override
    protected void inject(final ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected ScopedPresenter getPresenter() {
        return presenter;
    }
}

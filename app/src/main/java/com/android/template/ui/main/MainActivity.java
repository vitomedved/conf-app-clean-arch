package com.android.template.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.template.R;
import com.android.template.base.BaseActivity;
import com.android.template.base.ScopedPresenter;
import com.android.template.injection.activity.ActivityComponent;
import com.android.template.utils.ui.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import template.android.com.data.DataJavaInvoker;
import template.android.com.data.DataKotlinInvoker;
import template.android.com.device.DeviceJavaInvoker;
import template.android.com.device.DeviceKotlinInvoker;
import template.android.com.domain.DomainJavaInvoker;
import template.android.com.domain.DomainKotlinInvoker;

public final class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.activity_main_image_view)
    ImageView imageView;

    @Inject
    MainContract.Presenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To ensure all modules are well connected.
        new DataJavaInvoker().invoke();
        new DataKotlinInvoker().invoke();

        new DeviceJavaInvoker().invoke();
        new DeviceKotlinInvoker().invoke();

        new DomainJavaInvoker().invoke();
        new DomainKotlinInvoker().invoke();

        imageLoader.loadImage("http://www.tate.org.uk/art/images/work/T/T05/T05010_10.jpg",
                              imageView);

        presenter.init();
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

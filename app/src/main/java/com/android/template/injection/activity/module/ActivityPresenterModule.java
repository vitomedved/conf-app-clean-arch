package com.android.template.injection.activity.module;

import com.android.template.injection.activity.DaggerActivity;
import com.android.template.injection.scope.ActivityScope;
//import com.android.template.ui.home.HomeContract;
//import com.android.template.ui.home.HomePresenter;
import com.android.template.ui.main.MainContract;
import com.android.template.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityPresenterModule {

    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    MainContract.Presenter provideMainPresenter() {
        final MainPresenter presenter = new MainPresenter((MainContract.View) daggerActivity);
        daggerActivity.getActivityComponent().inject(presenter);

        return presenter;
    }

    public interface Exposes {

    }
}

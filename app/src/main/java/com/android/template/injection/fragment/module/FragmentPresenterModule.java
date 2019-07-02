package com.android.template.injection.fragment.module;

import com.android.template.injection.fragment.DaggerFragment;
import com.android.template.injection.scope.FragmentScope;
import com.android.template.ui.welcome.WelcomeContract;
import com.android.template.ui.welcome.WelcomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment fragment;

    public FragmentPresenterModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    WelcomeContract.Presenter provideWelcomePresenter() {
        final WelcomePresenter presenter = new WelcomePresenter((WelcomeContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    public interface Exposes {

    }
}

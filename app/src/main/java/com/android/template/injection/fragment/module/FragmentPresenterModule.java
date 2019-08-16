package com.android.template.injection.fragment.module;

import com.android.template.injection.fragment.DaggerFragment;
import com.android.template.injection.scope.FragmentScope;
import com.android.template.ui.schedule.ScheduleContract;
import com.android.template.ui.schedule.SchedulePresenter;
import com.android.template.ui.about.AboutContract;
import com.android.template.ui.about.AboutPresenter;
import com.android.template.ui.welcome.AddConferenceContract;
import com.android.template.ui.welcome.AddConferencePresenter;

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
    AddConferenceContract.Presenter provideWelcomePresenter() {
        final AddConferencePresenter presenter = new AddConferencePresenter((AddConferenceContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    @Provides
    @FragmentScope
    ScheduleContract.Presenter provideSchedulePresenter() {
        final SchedulePresenter presenter = new SchedulePresenter((ScheduleContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    @Provides
    @FragmentScope
    AboutContract.Presenter provideAboutPresenter() {
        final AboutPresenter presenter = new AboutPresenter((AboutContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    public interface Exposes {

    }
}

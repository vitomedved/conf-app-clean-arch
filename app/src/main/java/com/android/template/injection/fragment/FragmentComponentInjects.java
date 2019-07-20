package com.android.template.injection.fragment;

import com.android.template.ui.schedule.ScheduleFragment;
import com.android.template.ui.schedule.SchedulePresenter;
import com.android.template.ui.about.AboutFragment;
import com.android.template.ui.about.AboutPresenter;
import com.android.template.ui.welcome.AddConferenceFragment;
import com.android.template.ui.welcome.AddConferencePresenter;

public interface FragmentComponentInjects {

    void inject(AddConferenceFragment fragment);

    void inject(AddConferencePresenter presenter);

    void inject(ScheduleFragment fragment);

    void inject(SchedulePresenter presenter);

    void inject(AboutFragment fragment);

    void inject(AboutPresenter presenter);
}

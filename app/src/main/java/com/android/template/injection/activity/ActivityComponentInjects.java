package com.android.template.injection.activity;

import com.android.template.ui.event.EventActivity;
import com.android.template.ui.event.EventPresenter;
import com.android.template.ui.home.HomeActivity;
import com.android.template.ui.home.HomePresenter;
import com.android.template.ui.main.MainActivity;
import com.android.template.ui.main.MainPresenter;

public interface ActivityComponentInjects {

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(HomeActivity activity);

    void inject(HomePresenter presenter);

    void inject(EventActivity activity);

    void inject(EventPresenter presenter);
}

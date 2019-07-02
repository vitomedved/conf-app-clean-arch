package com.android.template.injection.fragment;

import com.android.template.ui.welcome.WelcomeFragment;
import com.android.template.ui.welcome.WelcomePresenter;

public interface FragmentComponentInjects {

    void inject(WelcomeFragment fragment);

    void inject(WelcomePresenter presenter);
}

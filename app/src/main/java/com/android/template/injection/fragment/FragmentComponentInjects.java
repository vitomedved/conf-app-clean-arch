package com.android.template.injection.fragment;

import com.android.template.ui.welcome.AddConferenceFragment;
import com.android.template.ui.welcome.AddConferencePresenter;

public interface FragmentComponentInjects {

    void inject(AddConferenceFragment fragment);

    void inject(AddConferencePresenter presenter);
}

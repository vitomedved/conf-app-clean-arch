package com.android.template.ui.main;

import com.android.template.base.BaseView;
import com.android.template.base.ScopedPresenter;

public final class MainContract {

    private MainContract() {

    }

    public interface Presenter extends ScopedPresenter {

        void showAddInitConferenceIdScreen();

        void trySkippingAddConferenceScreen();
    }

    public interface View extends BaseView {

    }
}

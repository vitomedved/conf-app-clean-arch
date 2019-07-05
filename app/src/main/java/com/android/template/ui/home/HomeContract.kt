package com.android.template.ui.home

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter

class HomeContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun showAboutConferenceScreen()
    }

    interface View : BaseView {

    }
}
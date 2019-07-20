package com.android.template.ui.about

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter
import template.android.com.domain.model.Conference

class AboutContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun init()
    }

    interface View : BaseView {
        fun render(conference: Conference)

        fun showGetInitialConferenceIdError()

        fun showGetConferenceDataError()
    }
}
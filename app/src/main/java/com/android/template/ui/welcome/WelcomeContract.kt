package com.android.template.ui.welcome

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter

class WelcomeContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun checkIfConferenceExists(id: String)
    }

    interface View : BaseView {
        fun showInvalidConferenceIdError()

        fun showConferenceDoesNotExistError()
    }
}
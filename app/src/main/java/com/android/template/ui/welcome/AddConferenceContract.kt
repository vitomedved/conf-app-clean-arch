package com.android.template.ui.welcome

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter

class AddConferenceContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun checkIfConferenceExists(id: String)

        fun indicateIfThisIsInitScreen(isInitScreen: Boolean)
    }

    interface View : BaseView {
        fun showInvalidConferenceIdError()

        fun showConferenceDoesNotExistError()

        fun showGetInitialConferenceError()
    }
}
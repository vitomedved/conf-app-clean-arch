package com.android.template.ui.home

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter
import template.android.com.domain.model.User

class HomeContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun init()

        fun handleUserSignedIn()

        fun signOutCurrentUser()

        fun showAboutConferenceScreen()
    }

    interface View : BaseView {
        fun showApplicationError()

        fun renderUserSignedInNavigationDrawer(user: User)

        fun renderUserNotSignedInNavigationDrawer()

        fun showSignOutError()

        fun renderAboutConferenceAsSelectedNavDrawerItem()
    }
}
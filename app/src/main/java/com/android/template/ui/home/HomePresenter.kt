package com.android.template.ui.home

import com.android.template.base.BasePresenter

class HomePresenter(view: HomeContract.View) : HomeContract.Presenter, BasePresenter<HomeContract.View>(view) {

    override fun renderNavigationBar() {
        // TODO (log in, log out buttons, conferences submenu, etc.)

    }

    override fun showAboutConferenceScreen() {
        router.showAboutConferenceScreen()
    }

    override fun signOutCurrentUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
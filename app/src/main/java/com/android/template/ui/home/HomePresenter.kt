package com.android.template.ui.home

import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.model.User
import template.android.com.domain.usecase.user.authentication.IsUserSignedInUseCase
import template.android.com.domain.usecase.user.authentication.SignOutUseCase
import template.android.com.domain.usecase.user.data.GetCurrentUserUseCase
import javax.inject.Inject

class HomePresenter(view: HomeContract.View) : HomeContract.Presenter, BasePresenter<HomeContract.View>(view) {

    @Inject
    lateinit var isUserSignedInUseCase: IsUserSignedInUseCase

    @Inject
    lateinit var getCurrentUserUseCase: GetCurrentUserUseCase

    @Inject
    lateinit var signOutUseCase: SignOutUseCase

    override fun init() {
        executeIsUserSignedInUseCase()
    }

    private fun executeIsUserSignedInUseCase() {
        addDisposable(isUserSignedInUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processIsUserSignedInUseCaseSuccess,
                                         this::processIsUserSignedInUseCaseError))
    }

    private fun processIsUserSignedInUseCaseSuccess(isUserSignedIn: Boolean) {
        if (isUserSignedIn) {
            executeGetCurrentUserUseCase()
        } else {
            doIfViewNotNull(HomeContract.View::renderUserNotSignedInNavigationDrawer)
            showAboutConferenceScreen()
        }
    }

    private fun processIsUserSignedInUseCaseError(throwable: Throwable) {
        doIfViewNotNull(HomeContract.View::showApplicationError)
        Log.e("HomePresenter", "Error with checking whether user is signed in: $throwable")
    }

    private fun executeGetCurrentUserUseCase() {
        addDisposable(getCurrentUserUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetCurrentUserUseCaseSuccess,
                                         this::processGetCurrentUserUseCaseError))
    }

    private fun processGetCurrentUserUseCaseSuccess(user: User) {
        doIfViewNotNull { view ->
            view.renderUserSignedInNavigationDrawer(user)
        }

        showAboutConferenceScreen()
    }

    private fun processGetCurrentUserUseCaseError(throwable: Throwable) {
        // TODO: maybe try logging out and let user know he must log back in
        Log.e("HomePresenter", "Error with getting current user: $throwable")
    }

    override fun handleUserSignedIn() {
        executeGetCurrentUserUseCase()
    }

    override fun signOutCurrentUser() {
        addDisposable(signOutUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processSignOutUseCaseSuccess,
                                         this::processSignOutUseCaseError))
    }

    private fun processSignOutUseCaseSuccess() {
        doIfViewNotNull(HomeContract.View::renderUserNotSignedInNavigationDrawer)
    }

    private fun processSignOutUseCaseError(throwable: Throwable) {
        doIfViewNotNull(HomeContract.View::showSignOutError)
        Log.e("HomePresenter", "Error with signing out: $throwable")
    }

    override fun showAboutConferenceScreen() {
        doIfViewNotNull(HomeContract.View::renderAboutConferenceAsSelectedNavDrawerItem)
        router.showAboutConferenceScreen()
    }

    override fun showScheduleScreen() {
        router.showScheduleScreen()
    }
}
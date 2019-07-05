package com.android.template.ui.welcome

import android.content.res.Resources
import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.usecase.DoesConferenceExistUseCase
import template.android.com.domain.utils.string.StringUtils
import javax.inject.Inject

class WelcomePresenter(view: WelcomeContract.View) : BasePresenter<WelcomeContract.View>(view), WelcomeContract.Presenter {

    @Inject
    lateinit var doesConferenceExistUseCase: DoesConferenceExistUseCase

    @Inject
    lateinit var stringUtils: StringUtils

    @Inject
    lateinit var resources: Resources

    override fun checkIfConferenceExists(id: String) {
        // TODO: Is it okay for user to input UID of conference (every conference is saved in firebase database by it's UID) to set initial conference? Or should I provide him with another ID which will reference certain conference?
        if (stringUtils.isEmpty(id)) {
            doIfViewNotNull(WelcomeContract.View::showInvalidConferenceIdError)
        } else {
            // TODO: open full-screen loading in fragment to disable all inputs and indicate that something is loading
            executeDoesConferenceExistUseCase(id)
        }
    }

    private fun executeDoesConferenceExistUseCase(id: String) {
        addDisposable(doesConferenceExistUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processDoesConferenceExistUseCaseSuccess,
                                         this::processDoesConferenceExistUseCaseError))
    }

    private fun processDoesConferenceExistUseCaseSuccess(doesConferenceExist: Boolean) {
        if (doesConferenceExist) {
            router.showHomeScreen()
        } else {
            doIfViewNotNull(WelcomeContract.View::showConferenceDoesNotExistError)
            // TODO: close full-screen loading in fragment
        }
    }

    private fun processDoesConferenceExistUseCaseError(throwable: Throwable) {
        Log.e("WelcomePresenter", "DoesConferenceExistUseCase returned an error: $throwable")
    }
}
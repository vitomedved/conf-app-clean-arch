package com.android.template.ui.welcome

import android.content.res.Resources
import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.usecase.conference.DoesConferenceExistUseCase
import template.android.com.domain.usecase.conference.SetInitialConferenceIdUseCase
import template.android.com.domain.utils.string.StringUtils
import javax.inject.Inject

class AddConferencePresenter(view: AddConferenceContract.View) : BasePresenter<AddConferenceContract.View>(view), AddConferenceContract.Presenter {

    @Inject
    lateinit var doesConferenceExistUseCase: DoesConferenceExistUseCase

    @Inject
    lateinit var setConferenceIdUseCase: SetInitialConferenceIdUseCase

    @Inject
    lateinit var stringUtils: StringUtils

    @Inject
    lateinit var resources: Resources

    // TODO: this variable will be used to differentiate setInitConferenceId (puts id in shared prefs) from AddNewConferenceId (saves id in database for logged in user)
    private var isInitScreen: Boolean = true

    override fun indicateIfThisIsInitScreen(isInitScreen: Boolean) {
        this.isInitScreen = isInitScreen
    }

    override fun checkIfConferenceExists(id: String) {
        if (stringUtils.isEmpty(id)) {
            doIfViewNotNull(AddConferenceContract.View::showInvalidConferenceIdError)
        } else {
            // TODO: open full-screen loading in fragment to disable all inputs and indicate that something is loading
            executeDoesConferenceExistUseCase(id)
        }
    }

    private fun executeDoesConferenceExistUseCase(id: String) {
        // TODO: Firebase Database paths must not contain '.', '#', '$', '[', or ']', put this in stringUtils to check for current id
        addDisposable(doesConferenceExistUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe({ conferenceExists -> processDoesConferenceExistUseCaseSuccess(conferenceExists, id) },
                                         this::processDoesConferenceExistUseCaseError,
                                         this::processDoesConferenceExistUseCaseComplete))
    }

    private fun processDoesConferenceExistUseCaseSuccess(doesConferenceExist: Boolean, id: String) {
        if (doesConferenceExist) {
            executeSetInitialConferenceIdUseCase(id)
        } else {
            doIfViewNotNull(AddConferenceContract.View::showConferenceDoesNotExistError)
            // TODO: close full-screen loading in fragment
        }
    }

    private fun processDoesConferenceExistUseCaseError(throwable: Throwable) {
        Log.e("AddConferencePresenter", "DoesConferenceExistUseCase returned an error (this can happen when url is scanned): $throwable")
        doIfViewNotNull(AddConferenceContract.View::showConferenceDoesNotExistError)
    }

    private fun processDoesConferenceExistUseCaseComplete() {
        Log.e("AddConferencePresenter", "Maybe returned with no result, I guess this should never happen.")
        doIfViewNotNull(AddConferenceContract.View::showConferenceDoesNotExistError)
    }

    private fun executeSetInitialConferenceIdUseCase(id: String) {
        addDisposable(setConferenceIdUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processSetInitialConferenceIdUseCaseSuccess,
                                         this::processSetInitialConferenceIdUseCaseError))
    }

    private fun processSetInitialConferenceIdUseCaseSuccess() {
        // TODO: close full-screen loading in fragment
        router.showHomeScreen()
    }

    private fun processSetInitialConferenceIdUseCaseError(throwable: Throwable) {
        Log.d("AddConferencePresenter", "There was a problem setting initial conference ID: $throwable")
    }
}

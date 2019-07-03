package com.android.template.ui.welcome

import android.content.res.Resources
import android.util.Log
import com.android.template.R
import com.android.template.base.BasePresenter
import com.google.zxing.integration.android.IntentIntegrator
import template.android.com.domain.usecase.SetConferenceIdUseCase
import template.android.com.domain.utils.string.StringUtils
import javax.inject.Inject

class WelcomePresenter(view: WelcomeContract.View) : BasePresenter<WelcomeContract.View>(view), WelcomeContract.Presenter {

    @Inject
    lateinit var setConferenceIdUseCase: SetConferenceIdUseCase

    @Inject
    lateinit var stringUtils: StringUtils

    @Inject
    lateinit var resources: Resources

    override fun setConferenceId(id: String) {
        // TODO: Is it okay for user to input UID of conference (every conference is saved in firebase database by it's UID) to set initial conference? Or should I provide him with another ID which will reference certain conference?
        if (stringUtils.isEmpty(id) || (id.length < resources.getInteger(R.integer.welcome_fragment_conference_id_input_length))) {
            doIfViewNotNull(WelcomeContract.View::showInvalidConferenceIdError)
        } else {
            executeSetConferenceIdUseCase(id)
        }
    }

    private fun executeSetConferenceIdUseCase(id: String) {
        addDisposable(setConferenceIdUseCase.execute(id)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(this::processSetConferenceIdUseCaseSuccess,
                        this::processSetConferenceIdUseCaseError))
    }

    private fun processSetConferenceIdUseCaseSuccess(doesConferenceExist: Boolean) {
        if (doesConferenceExist) {
            // TODO: reroute to homepage
        } else {
            doIfViewNotNull(WelcomeContract.View::showConferenceDoesNotExistError)
        }
    }

    private fun processSetConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("WelcomePresenter", "SetConferenceIdUseCase returned an error: $throwable")
    }
}
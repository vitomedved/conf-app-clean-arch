package com.android.template.ui.about

import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.model.Conference
import template.android.com.domain.usecase.conference.data.GetConferenceDataUseCase
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase
import javax.inject.Inject

class AboutPresenter(view: AboutContract.View) : BasePresenter<AboutContract.View>(view), AboutContract.Presenter {

    @Inject
    lateinit var getInitialConferenceIdUseCase: GetInitialConferenceIdUseCase

    @Inject
    lateinit var getConferenceDataUseCase: GetConferenceDataUseCase

    override fun init() {
        addDisposable(getInitialConferenceIdUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetInitialConferenceIdUseCaseSuccess,
                                         this::processGetInitialConferenceIdUseCaseError))
    }

    private fun processGetInitialConferenceIdUseCaseSuccess(id: String) {
        executeGetConferenceDataUseCase(id)
    }

    private fun processGetInitialConferenceIdUseCaseError(throwable: Throwable) {
        // TODO: maybe show some kind of toast saying data can not be loaded
        Log.e("AboutPresenter", "Error with getting current initial conference id: $throwable")
    }

    private fun executeGetConferenceDataUseCase(id: String) {
        addDisposable(getConferenceDataUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetConferenceDataUseCaseSuccess,
                                         this::processGetConferenceDataUseCaseError))
    }

    private fun processGetConferenceDataUseCaseSuccess(conferenceData: Conference) {
        doIfViewNotNull { view ->
            view.render(conferenceData)
        }
    }

    private fun processGetConferenceDataUseCaseError(throwable: Throwable) {
        // TODO: maybe show some toast message
        Log.e("AboutPresenter", "Error with getting conference data: $throwable")
    }
}
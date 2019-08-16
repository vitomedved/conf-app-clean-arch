package com.android.template.ui.event

import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase
import template.android.com.domain.usecase.event.GetEventDataByConferenceIdAndEventIdUseCase
import template.android.com.domain.usecase.event.GetEventInfoByConferenceIdAndEventIdUseCase
import template.android.com.domain.utils.string.StringUtils
import javax.inject.Inject

class EventPresenter(view: EventContract.View) : BasePresenter<EventContract.View>(view), EventContract.Presenter {

    @Inject
    lateinit var getInitialConferenceIdUseCase: GetInitialConferenceIdUseCase

    @Inject
    lateinit var getEventInfoByConferenceIdAndEventIdUseCase: GetEventInfoByConferenceIdAndEventIdUseCase

    @Inject
    lateinit var getEventDataByConferenceIdAndEventIdUseCase: GetEventDataByConferenceIdAndEventIdUseCase

    @Inject
    lateinit var stringUtils: StringUtils

    var eventId: String = ""
    var conferenceId: String = ""

    override fun initEvent(eventId: String) {
        this.eventId = eventId

        doIfViewNotNull(EventContract.View::hideAllContent)
        doIfViewNotNull(EventContract.View::showLoading)

        executeGetInitialConferenceIdUseCase()
    }

    private fun executeGetInitialConferenceIdUseCase() {
        addDisposable(getInitialConferenceIdUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::getInitialConferenceIdUseCaseSuccess,
                                         this::getInitialConferenceIdUseCaseError))
    }

    private fun getInitialConferenceIdUseCaseSuccess(conferenceId: String) {
        this.conferenceId = conferenceId

        executeGetEventInfoByConferenceIdAndEventIdUseCase()
    }

    private fun getInitialConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("EventPresenter", "Error with getting conference ID: $throwable")
    }

    private fun executeGetEventInfoByConferenceIdAndEventIdUseCase() {
        if ((!stringUtils.isEmpty(conferenceId)) && (!stringUtils.isEmpty(eventId))) {
            addDisposable(getEventInfoByConferenceIdAndEventIdUseCase.execute(conferenceId, eventId)
                                  .subscribeOn(backgroundScheduler)
                                  .observeOn(mainThreadScheduler)
                                  .subscribe(this::getEventInfoByConferenceIdAndEventIdUseCaseSuccess,
                                             this::getEventInfoByConferenceIdAndEventIdUseCaseError))
        }
    }

    private fun getEventInfoByConferenceIdAndEventIdUseCaseSuccess(eventInfo: EventInfo) {
        doIfViewNotNull { view ->
            view.renderEventInfo(eventInfo)
            view.showEventInfoContent()
        }
        executeGetEventDataByConferenceIdAndEventIdUseCase()
    }

    private fun getEventInfoByConferenceIdAndEventIdUseCaseError(throwable: Throwable) {
        Log.e("EventPresenter", "Error with getting event data: $throwable")
    }

    private fun executeGetEventDataByConferenceIdAndEventIdUseCase() {
        if ((!stringUtils.isEmpty(conferenceId)) && (!stringUtils.isEmpty(eventId))) {
            addDisposable(getEventDataByConferenceIdAndEventIdUseCase.execute(conferenceId, eventId)
                                  .subscribeOn(backgroundScheduler)
                                  .observeOn(mainThreadScheduler)
                                  .subscribe(this::getEventDataByConferenceIdAndEventIdUseCaseSuccess,
                                             this::getEventDataByConferenceIdAndEventIdUseCaseError))
        }
    }

    private fun getEventDataByConferenceIdAndEventIdUseCaseSuccess(eventData: EventData) {
        doIfViewNotNull { view ->
            view.renderEventData(eventData)
            view.showEventDataContent()

            // TODO: REMOVE THIS WHEN COMMENTS/PRESENTERS USE CASES ARE ADDED
            view.hideLoading()
        }

        // TODO: execute get comments (maybe also get presenters), hide loading
    }

    private fun getEventDataByConferenceIdAndEventIdUseCaseError(throwable: Throwable) {
        Log.e("EventPresenter", "Error with getting event info: $throwable")
    }

}
package com.android.template.ui.schedule

import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.model.EventInfo
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase
import template.android.com.domain.usecase.event.GetEventInfoListByConferenceIdUseCase
import javax.inject.Inject

class SchedulePresenter(view: ScheduleContract.View) : BasePresenter<ScheduleContract.View>(view), ScheduleContract.Presenter {

    @Inject
    lateinit var getInitialConferenceIdUseCase: GetInitialConferenceIdUseCase

    @Inject
    lateinit var getEventInfoListByConferenceIdUseCase: GetEventInfoListByConferenceIdUseCase


    override fun init() {
        doIfViewNotNull(ScheduleContract.View::hideRecyclerView)

        executeGetInitialConferenceIdUseCase()
    }

    private fun executeGetInitialConferenceIdUseCase() {
        addDisposable(getInitialConferenceIdUseCase.execute()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetInitialConferenceIdUseCaseSuccess,
                                         this::processGetInitialConferenceIdUseCaseError))
    }

    private fun processGetInitialConferenceIdUseCaseSuccess(id: String) {
        executeGetEventsByConferenceIdUseCase(id)
    }

    private fun processGetInitialConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "There was an error with fetching current conference ID: $throwable")
    }

    private fun executeGetEventsByConferenceIdUseCase(id: String) {
        addDisposable(getEventInfoListByConferenceIdUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetEventsByConferenceIdUseCaseSuccess,
                                         this::processGetEventsByConferenceIdUseCaseError))
    }

    private fun processGetEventsByConferenceIdUseCaseSuccess(eventInfos: List<EventInfo>) {
        doIfViewNotNull { view ->
            view.render(eventInfos)
            view.hideLoading()
            view.showRecyclerView()
        }
    }

    private fun processGetEventsByConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "There was an error with fetching events for current conference: $throwable")
    }
}
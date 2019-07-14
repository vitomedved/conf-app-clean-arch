package com.android.template.ui.schedule

import android.util.Log
import com.android.template.base.BasePresenter
import template.android.com.domain.model.Event
import template.android.com.domain.usecase.conference.GetInitialConferenceIdUseCase
import template.android.com.domain.usecase.event.GetEventsByConferenceIdUseCase
import javax.inject.Inject

class SchedulePresenter(view: ScheduleContract.View) : BasePresenter<ScheduleContract.View>(view), ScheduleContract.Presenter {

    @Inject
    lateinit var getInitialConferenceIdUseCase: GetInitialConferenceIdUseCase

    @Inject
    lateinit var getEventsByConferenceIdUseCase: GetEventsByConferenceIdUseCase


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
        addDisposable(getEventsByConferenceIdUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetEventsByConferenceIdUseCaseSuccess,
                                         this::processGetEventsByConferenceIdUseCaseError))
    }

    private fun processGetEventsByConferenceIdUseCaseSuccess(events: List<Event>) {
        doIfViewNotNull { view ->
            view.render(events)
            view.hideLoading()
            view.showRecyclerView()
        }
    }

    private fun processGetEventsByConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "There was an error with fetching events for current conference: $throwable")
    }
}
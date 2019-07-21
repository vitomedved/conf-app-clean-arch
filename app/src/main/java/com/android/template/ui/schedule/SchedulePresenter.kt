package com.android.template.ui.schedule

import android.util.Log
import com.android.template.base.BasePresenter
import com.android.template.utils.calendar.CalendarFactory
import com.android.template.utils.calendar.CalendarUtils
import template.android.com.domain.model.ConferenceDates
import template.android.com.domain.model.EventInfo
import template.android.com.domain.usecase.conference.data.GetConferenceDatesUseCase
import template.android.com.domain.usecase.conference.initial.GetInitialConferenceIdUseCase
import template.android.com.domain.usecase.event.GetEventInfoListByConferenceIdUseCase
import java.util.*
import javax.inject.Inject

class SchedulePresenter(view: ScheduleContract.View) : BasePresenter<ScheduleContract.View>(view), ScheduleContract.Presenter {

    @Inject
    lateinit var getInitialConferenceIdUseCase: GetInitialConferenceIdUseCase

    @Inject
    lateinit var getEventInfoListByConferenceIdUseCase: GetEventInfoListByConferenceIdUseCase

    @Inject
    lateinit var getConferenceDatesUseCase: GetConferenceDatesUseCase

    @Inject
    lateinit var calendarFactory: CalendarFactory

    @Inject
    lateinit var calendarUtils: CalendarUtils

    private lateinit var conferenceDates: ConferenceDates

    private val currentDate: Calendar = Calendar.getInstance()

    private val eventInfoList: MutableList<EventInfo> = mutableListOf()

    override fun init() {
        doIfViewNotNull(ScheduleContract.View::hideRecyclerView)
        doIfViewNotNull(ScheduleContract.View::hideHeaderDate)
        doIfViewNotNull(ScheduleContract.View::disableHeaderButtonLeft)
        doIfViewNotNull(ScheduleContract.View::disableHeaderButtonRight)

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
        executeGetConferenceDatesUseCase(id)
    }

    private fun processGetInitialConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "There was an error with fetching current conference ID: $throwable")
    }

    private fun executeGetConferenceDatesUseCase(id: String) {
        addDisposable(getConferenceDatesUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe({ conferenceDates -> processGetConferenceDatesUseCaseSuccess(id, conferenceDates) },
                                         this::processGetConferenceDatesUseCaseError))
    }

    private fun processGetConferenceDatesUseCaseSuccess(id: String, conferenceDates: ConferenceDates) {
        this.conferenceDates = conferenceDates
        initCurrentDate()

        executeGetEventInfoListByConferenceIdUseCase(id)
    }

    private fun initCurrentDate() {
        currentDate.time = calendarFactory.dateStringToCalendar(conferenceDates.startDate)
                .time
        renderCurrentDate()
    }

    private fun renderCurrentDate() {
        doIfViewNotNull { view ->
            view.renderHeaderDateNumber(calendarUtils.getDayOfMonthNumber(currentDate))
            view.renderHeaderMonth(calendarUtils.getShortMonthName(currentDate))
            view.renderHeaderWeekday(calendarUtils.getShortWeekdayName(currentDate))
        }
    }

    private fun processGetConferenceDatesUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "Error with getting conference dates: $throwable")
    }

    private fun executeGetEventInfoListByConferenceIdUseCase(id: String) {
        addDisposable(getEventInfoListByConferenceIdUseCase.execute(id)
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainThreadScheduler)
                              .subscribe(this::processGetEventInfoListByConferenceIdUseCaseSuccess,
                                         this::processGetEventInfoListByConferenceIdUseCaseError))
    }

    private fun processGetEventInfoListByConferenceIdUseCaseSuccess(eventInfoList: List<EventInfo>) {
        this.eventInfoList.clear()
        this.eventInfoList.addAll(eventInfoList)

        renderCurrentEventInfoList()

        doIfViewNotNull { view ->
            view.hideLoading()
            view.showHeaderDate()
            view.enableHeaderButtonRight()
            view.showRecyclerView()
        }
    }

    private fun processGetEventInfoListByConferenceIdUseCaseError(throwable: Throwable) {
        Log.e("SchedulePresenter", "There was an error with fetching events for current conference: $throwable")
    }

    private fun renderCurrentEventInfoList() {
        doIfViewNotNull { view ->
            view.render(eventInfoList.filter { eventInfo -> calendarUtils.isCalendarDateEqualToStringDate(currentDate, eventInfo.date) }
                                .sortedBy { eventInfo -> eventInfo.startingTime })
        }
    }

    override fun subtractDay() {
        if (!calendarUtils.subtractDayWithStringDateLimit(currentDate, conferenceDates.startDate)) {
            doIfViewNotNull(ScheduleContract.View::disableHeaderButtonLeft)
            return
        }
        // RATIONALE: If I was able to subtract a day, I can 100% add 1 day therefore enable this button.
        // The button may be disabled from special if case.
        doIfViewNotNull(ScheduleContract.View::enableHeaderButtonRight)
        renderCurrentDate()
        renderCurrentEventInfoList()
    }

    override fun addDay() {
        if (!calendarUtils.addDayWithStringDateLimit(currentDate, conferenceDates.endDate)) {
            doIfViewNotNull(ScheduleContract.View::disableHeaderButtonRight)
            return
        }
        // RATIONALE: If I was able to add a day, I can 100% subtract 1 day therefore enable this button.
        // The button may be disabled from special if case.
        doIfViewNotNull(ScheduleContract.View::enableHeaderButtonLeft)
        renderCurrentDate()
        renderCurrentEventInfoList()
    }
}
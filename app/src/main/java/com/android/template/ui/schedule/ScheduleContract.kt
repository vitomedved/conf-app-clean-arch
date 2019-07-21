package com.android.template.ui.schedule

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter
import template.android.com.domain.model.EventInfo
import java.util.*

class ScheduleContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun init()

        fun subtractDay()

        fun addDay()
    }

    interface View : BaseView {
        fun hideRecyclerView()

        fun showRecyclerView()

        fun hideHeaderDate()

        fun showHeaderDate()

        fun disableHeaderButtonLeft()

        fun enableHeaderButtonLeft()

        fun disableHeaderButtonRight()

        fun enableHeaderButtonRight()

        fun render(eventInfoList: List<EventInfo>)

        fun hideLoading()

        fun renderHeaderDateNumber(dateNumber: Int)

        fun renderHeaderMonth(shortMonthName: String)

        fun renderHeaderWeekday(shortWeekdayName: String)
    }
}
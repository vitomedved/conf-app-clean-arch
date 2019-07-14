package com.android.template.ui.schedule

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter
import template.android.com.domain.model.Event

class ScheduleContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun init()
    }

    interface View : BaseView {
        fun hideRecyclerView()

        fun showRecyclerView()

        fun render(events: List<Event>)

        fun hideLoading()
    }
}
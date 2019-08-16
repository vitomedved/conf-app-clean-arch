package com.android.template.ui.event

import com.android.template.base.BaseView
import com.android.template.base.ScopedPresenter
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo

class EventContract private constructor() {
    interface Presenter : ScopedPresenter {
        fun initEvent(eventId: String)
    }

    interface View : BaseView {
        fun renderEventInfo(eventInfo: EventInfo)

        fun renderEventData(eventData: EventData)

        fun hideAllContent()

        fun showEventInfoContent()

        fun showEventDataContent()

        fun showLoading()

        fun hideLoading()
    }
}
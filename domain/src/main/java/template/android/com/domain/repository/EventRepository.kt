package template.android.com.domain.repository

import io.reactivex.Single
import template.android.com.domain.model.EventInfo

interface EventRepository {
    fun getEventsByConferenceId(id: String): Single<List<EventInfo>>

}
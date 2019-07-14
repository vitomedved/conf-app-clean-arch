package template.android.com.domain.repository

import io.reactivex.Single
import template.android.com.domain.model.Event

interface EventRepository {
    fun getEventsByConferenceId(id: String): Single<List<Event>>

}
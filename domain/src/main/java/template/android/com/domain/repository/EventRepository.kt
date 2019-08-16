package template.android.com.domain.repository

import io.reactivex.Single
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo

interface EventRepository {
    fun getEventsByConferenceId(id: String): Single<List<EventInfo>>

    fun getEventInfoByConferenceIdAndEventId(conferenceId: String, eventId: String): Single<EventInfo>

    fun getEventDataByConferenceIdAndEventId(conferenceId: String, eventId: String): Single<EventData>

}
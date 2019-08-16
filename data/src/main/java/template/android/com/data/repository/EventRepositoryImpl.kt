package template.android.com.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import template.android.com.data.firebase.mapper.FirebaseMapper
import template.android.com.data.firebase.model.FirebaseEventData
import template.android.com.data.firebase.model.FirebaseEventInfo
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo
import template.android.com.domain.repository.EventRepository

class EventRepositoryImpl(private val firebaseMapper: FirebaseMapper) : EventRepository {

    companion object {
        private const val EVENT_INFO_BY_CONFERENCE_ID_KEY = "eventInfoByConferenceId"
        private const val EVENT_DATA_BY_CONFERENCE_ID_KEY = "eventDataByConferenceId"
    }

    override fun getEventsByConferenceId(id: String): Single<List<EventInfo>> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(EVENT_INFO_BY_CONFERENCE_ID_KEY)
                .child(id)

        return RxFirebaseDatabase.observeSingleValueEvent(reference) { eventInfoListSnapshot: DataSnapshot ->
            eventInfoListSnapshot.children
                    .map { eventInfoSnapshot ->
                        firebaseMapper.mapToEventInfo(eventInfoSnapshot.getValue(FirebaseEventInfo::class.java)) }
        }
                .toSingle()
    }

    override fun getEventInfoByConferenceIdAndEventId(conferenceId: String, eventId: String): Single<EventInfo> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(EVENT_INFO_BY_CONFERENCE_ID_KEY)
                .child(conferenceId)
                .child(eventId)

        return RxFirebaseDatabase.observeSingleValueEvent(reference) { eventInfoDataSnapshot: DataSnapshot ->
            firebaseMapper.mapToEventInfo(eventInfoDataSnapshot.getValue(FirebaseEventInfo::class.java))
        }
                .toSingle()
    }

    override fun getEventDataByConferenceIdAndEventId(conferenceId: String, eventId: String): Single<EventData> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(EVENT_DATA_BY_CONFERENCE_ID_KEY)
                .child(conferenceId)
                .child(eventId)

        return RxFirebaseDatabase.observeSingleValueEvent(reference) { eventDataDataSnapshot: DataSnapshot ->
            firebaseMapper.mapToEventData(eventDataDataSnapshot.getValue(FirebaseEventData::class.java))
        }
                .toSingle()
    }
}
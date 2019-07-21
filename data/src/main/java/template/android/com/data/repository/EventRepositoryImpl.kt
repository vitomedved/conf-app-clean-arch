package template.android.com.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import template.android.com.data.firebase.mapper.FirebaseMapper
import template.android.com.data.firebase.model.FirebaseEventInfo
import template.android.com.domain.model.EventInfo
import template.android.com.domain.repository.EventRepository

class EventRepositoryImpl(private val firebaseMapper: FirebaseMapper) : EventRepository {

    companion object {
        private const val EVENT_INFO_BY_CONFERENCE_ID_KEY = "eventInfoByConferenceId"
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
}
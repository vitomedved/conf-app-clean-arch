package template.android.com.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Observable
import io.reactivex.Single
import template.android.com.data.firebase.mapper.FirebaseMapper
import template.android.com.data.firebase.model.FirebaseConference
import template.android.com.domain.model.Conference
import template.android.com.domain.model.ConferenceDates
import template.android.com.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl(private val firebaseMapper: FirebaseMapper) : ConferenceRepository {

    private val CONFERENCE_IDS_KEY = "conferenceIds"
    private val CONFERENCE_DATA_KEY = "conferences"

    override fun fetchDoesConferenceIdExist(id: String): Observable<Boolean> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(CONFERENCE_IDS_KEY)
                .child(id)
        return RxFirebaseDatabase.observeSingleValueEvent(reference) { it.exists() }
                .toObservable()
    }

    override fun fetchConferenceData(id: String): Observable<Conference> {
        // This reference will fetch conferences/id object from database
        val reference = FirebaseDatabase.getInstance()
                .getReference(CONFERENCE_DATA_KEY)
                .child(id)

        return RxFirebaseDatabase.observeSingleValueEvent(reference) { conferenceDataSnapshot: DataSnapshot ->
            firebaseMapper.mapToConference(conferenceDataSnapshot.getValue(FirebaseConference::class.java))
        }
                .toObservable()
    }

    override fun fetchConferenceDates(id: String): Single<ConferenceDates> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(CONFERENCE_DATA_KEY)
                .child(id)

        return RxFirebaseDatabase.observeSingleValueEvent(reference) { conferenceDataSnapshot: DataSnapshot ->
            firebaseMapper.mapToConferenceDates(conferenceDataSnapshot.getValue(FirebaseConference::class.java))
        }
                .toSingle()
    }
}
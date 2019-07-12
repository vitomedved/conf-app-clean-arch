package template.android.com.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe
import io.reactivex.Observable
import template.android.com.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl : ConferenceRepository {

    private val CONFERENCE_IDS_KEY = "conferenceIds"

    override fun fetchDoesConferenceIdExist(id: String): Maybe<Boolean> {
        val reference = FirebaseDatabase.getInstance()
                .getReference(CONFERENCE_IDS_KEY)
                .child(id)
        return RxFirebaseDatabase.observeSingleValueEvent(reference) { it.exists() }

        /*return Observable.create { emitter ->
            FirebaseDatabase.getInstance().getReference(CONFERENCE_IDS_KEY).child(id).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    emitter.onNext(dataSnapshot.exists())
                    emitter.onComplete()
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                    emitter.onError(dataSnapshot.toException())
                }
            })
        }*/
    }
}
package template.android.com.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import template.android.com.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl : ConferenceRepository {
    override fun fetchDoesConferenceIdExist(id: String): Observable<Boolean> {
        return Observable.create { emitter ->
            FirebaseDatabase.getInstance().getReference("conferenceIds").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    emitter.onNext(dataSnapshot.exists())
                    emitter.onComplete()
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                    emitter.onError(dataSnapshot.toException())
                }
            })
        }
    }
}
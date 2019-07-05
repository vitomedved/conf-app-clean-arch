package template.android.com.domain.repository

import io.reactivex.Observable

interface ConferenceRepository {
    fun fetchDoesConferenceIdExist(id: String): Observable<Boolean>
}
package template.android.com.domain.repository

import io.reactivex.Observable
import template.android.com.domain.model.Conference

interface ConferenceRepository {
    fun fetchDoesConferenceIdExist(id: String): Observable<Boolean>

    fun fetchConferenceData(id: String): Observable<Conference>
}
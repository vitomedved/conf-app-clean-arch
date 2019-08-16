package template.android.com.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import template.android.com.domain.model.Conference
import template.android.com.domain.model.ConferenceDates

interface ConferenceRepository {
    // TODO: maybe use Single instead of Observable, only 1 data is returned
    fun fetchDoesConferenceIdExist(id: String): Observable<Boolean>

    fun fetchConferenceData(id: String): Observable<Conference>

    fun fetchConferenceDates(id: String): Single<ConferenceDates>
}
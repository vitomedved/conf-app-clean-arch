package template.android.com.domain.repository

import io.reactivex.Maybe

interface ConferenceRepository {
    fun fetchDoesConferenceIdExist(id: String): Maybe<Boolean>
}
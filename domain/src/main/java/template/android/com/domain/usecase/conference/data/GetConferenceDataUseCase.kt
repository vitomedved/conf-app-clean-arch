package template.android.com.domain.usecase.conference.data

import io.reactivex.Observable
import template.android.com.domain.model.Conference

interface GetConferenceDataUseCase {
    fun execute(id: String): Observable<Conference>
}
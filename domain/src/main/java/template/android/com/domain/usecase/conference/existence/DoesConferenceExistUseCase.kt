package template.android.com.domain.usecase.conference.existence

import io.reactivex.Observable

interface DoesConferenceExistUseCase {
    fun execute(id: String): Observable<Boolean>
}
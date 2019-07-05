package template.android.com.domain.usecase.conference

import io.reactivex.Observable

interface DoesConferenceExistUseCase {
    fun execute(id: String): Observable<Boolean>
}
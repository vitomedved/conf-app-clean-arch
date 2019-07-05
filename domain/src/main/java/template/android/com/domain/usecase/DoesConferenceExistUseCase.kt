package template.android.com.domain.usecase

import io.reactivex.Observable

interface DoesConferenceExistUseCase {
    fun execute(id: String): Observable<Boolean>
}
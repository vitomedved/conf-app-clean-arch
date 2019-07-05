package template.android.com.domain.usecase.conference

import io.reactivex.Completable

interface SetInitialConferenceIdUseCase {
    fun execute(id: String): Completable
}
package template.android.com.domain.usecase.conference.initial

import io.reactivex.Completable

interface SetInitialConferenceIdUseCase {
    fun execute(id: String): Completable
}
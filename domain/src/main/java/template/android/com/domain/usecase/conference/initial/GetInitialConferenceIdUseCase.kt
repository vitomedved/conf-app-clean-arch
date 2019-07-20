package template.android.com.domain.usecase.conference.initial

import io.reactivex.Single

interface GetInitialConferenceIdUseCase {
    fun execute(): Single<String>
}
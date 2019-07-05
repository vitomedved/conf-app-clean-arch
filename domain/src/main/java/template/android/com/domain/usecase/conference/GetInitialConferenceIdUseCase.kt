package template.android.com.domain.usecase.conference

import io.reactivex.Single

interface GetInitialConferenceIdUseCase {
    fun execute(): Single<String>
}
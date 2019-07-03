package template.android.com.domain.usecase

import io.reactivex.Single

interface SetConferenceIdUseCase {
    fun execute(id: String): Single<Boolean>
}
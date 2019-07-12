package template.android.com.domain.usecase.conference

import io.reactivex.Maybe
import io.reactivex.Single

interface DoesConferenceExistUseCase {
    fun execute(id: String): Maybe<Boolean>
}
package template.android.com.domain.usecase.conference.data

import io.reactivex.Single
import template.android.com.domain.model.ConferenceDates

interface GetConferenceDatesUseCase {
    fun execute(id: String): Single<ConferenceDates>
}
package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.Event

interface GetEventsByConferenceIdUseCase {
    fun execute(id: String): Single<List<Event>>
}
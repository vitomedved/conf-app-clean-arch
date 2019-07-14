package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.Event
import template.android.com.domain.repository.EventRepository

class GetEventsByConferenceIdUseCaseImpl (private val eventRepository: EventRepository): GetEventsByConferenceIdUseCase {
    override fun execute(id: String): Single<List<Event>> {
        return eventRepository.getEventsByConferenceId(id)
    }
}
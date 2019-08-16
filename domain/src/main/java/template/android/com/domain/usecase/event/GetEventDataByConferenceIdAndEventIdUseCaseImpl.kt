package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventData
import template.android.com.domain.repository.EventRepository

// TODO: I could do only search by event id, but this will be faster because of my firebase structure.
class GetEventDataByConferenceIdAndEventIdUseCaseImpl(private val eventRepository: EventRepository) : GetEventDataByConferenceIdAndEventIdUseCase {
    override fun execute(conferenceId: String, eventId: String): Single<EventData> {
        return eventRepository.getEventDataByConferenceIdAndEventId(conferenceId, eventId)
    }
}
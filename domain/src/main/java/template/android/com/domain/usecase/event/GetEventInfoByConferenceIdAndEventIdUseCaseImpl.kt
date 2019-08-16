package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventInfo
import template.android.com.domain.repository.EventRepository

class GetEventInfoByConferenceIdAndEventIdUseCaseImpl(private val eventRepository: EventRepository) : GetEventInfoByConferenceIdAndEventIdUseCase {
    override fun execute(conferenceId: String, eventId: String): Single<EventInfo> {
        return eventRepository.getEventInfoByConferenceIdAndEventId(conferenceId, eventId)
    }
}
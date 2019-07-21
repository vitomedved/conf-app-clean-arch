package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventInfo
import template.android.com.domain.repository.EventRepository

class GetEventInfoListByConferenceIdUseCaseImpl (private val eventRepository: EventRepository): GetEventInfoListByConferenceIdUseCase {
    override fun execute(id: String): Single<List<EventInfo>> {
        return eventRepository.getEventsByConferenceId(id)
    }
}
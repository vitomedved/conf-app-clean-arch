package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventInfo

interface GetEventInfoByConferenceIdAndEventIdUseCase {
    fun execute(conferenceId: String, eventId: String): Single<EventInfo>
}
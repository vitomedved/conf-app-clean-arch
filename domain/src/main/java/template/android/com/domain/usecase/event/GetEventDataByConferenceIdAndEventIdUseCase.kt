package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventData

interface GetEventDataByConferenceIdAndEventIdUseCase {
    fun execute(conferenceId: String, eventId: String): Single<EventData>
}
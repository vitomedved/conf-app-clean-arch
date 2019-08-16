package template.android.com.domain.usecase.event

import io.reactivex.Single
import template.android.com.domain.model.EventInfo

interface GetEventInfoListByConferenceIdUseCase {
    fun execute(id: String): Single<List<EventInfo>>
}
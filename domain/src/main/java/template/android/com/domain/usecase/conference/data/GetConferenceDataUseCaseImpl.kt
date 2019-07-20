package template.android.com.domain.usecase.conference.data

import io.reactivex.Observable
import template.android.com.domain.model.Conference
import template.android.com.domain.repository.ConferenceRepository

class GetConferenceDataUseCaseImpl (private val conferenceRepository: ConferenceRepository) : GetConferenceDataUseCase {
    override fun execute(id: String): Observable<Conference> {
        return conferenceRepository.fetchConferenceData(id)
    }
}
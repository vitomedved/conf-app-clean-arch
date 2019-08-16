package template.android.com.domain.usecase.conference.data

import io.reactivex.Single
import template.android.com.domain.model.ConferenceDates
import template.android.com.domain.repository.ConferenceRepository

class GetConferenceDatesUseCaseImpl(private val conferenceRepository: ConferenceRepository) : GetConferenceDatesUseCase {
    override fun execute(id: String): Single<ConferenceDates> {
        return conferenceRepository.fetchConferenceDates(id)
    }
}
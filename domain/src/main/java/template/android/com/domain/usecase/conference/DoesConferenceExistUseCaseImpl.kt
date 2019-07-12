package template.android.com.domain.usecase.conference

import io.reactivex.Maybe
import template.android.com.domain.repository.ConferenceRepository

class DoesConferenceExistUseCaseImpl (private val conferenceRepository: ConferenceRepository) : DoesConferenceExistUseCase {
    override fun execute(id: String): Maybe<Boolean> {
        // TODO: connect with firebase. Where to implement firebase? Should it be through repository and then implementation in data/firebase package?
        return conferenceRepository.fetchDoesConferenceIdExist(id)
    }
}
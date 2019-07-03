package template.android.com.domain.usecase

import io.reactivex.Single

class SetConferenceIdUseCaseImpl : SetConferenceIdUseCase {
    override fun execute(id: String): Single<Boolean> {
        // TODO: connect with firebase. Where to implement firebase? Should it be through repository and then implementation in data/firebase package?
        return Single.fromCallable { false }
    }
}
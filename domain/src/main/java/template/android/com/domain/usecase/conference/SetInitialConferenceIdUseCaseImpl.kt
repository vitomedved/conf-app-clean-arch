package template.android.com.domain.usecase.conference

import io.reactivex.Completable
import template.android.com.domain.delegate.ApplicationStorageDelegate

class SetInitialConferenceIdUseCaseImpl (private val applicationStorageDelegate: ApplicationStorageDelegate) : SetInitialConferenceIdUseCase {
    override fun execute(id: String): Completable {
        return Completable.fromAction { applicationStorageDelegate.saveConferenceId(id) }
    }
}
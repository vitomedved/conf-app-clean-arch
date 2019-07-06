package template.android.com.domain.usecase.conference

import io.reactivex.Single
import template.android.com.domain.delegate.ApplicationStorageDelegate

class GetInitialConferenceIdUseCaseImpl (private val applicationStorageDelegate: ApplicationStorageDelegate) : GetInitialConferenceIdUseCase {
    override fun execute(): Single<String> {
        return Single.fromCallable { applicationStorageDelegate.getConferenceId().get() }
    }
}
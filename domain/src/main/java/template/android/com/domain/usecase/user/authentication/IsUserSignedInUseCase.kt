package template.android.com.domain.usecase.user.authentication

import io.reactivex.Single

interface IsUserSignedInUseCase {
    fun execute(): Single<Boolean>
}
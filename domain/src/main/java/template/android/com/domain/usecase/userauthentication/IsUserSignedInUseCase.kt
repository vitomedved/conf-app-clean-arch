package template.android.com.domain.usecase.userauthentication

import io.reactivex.Single

interface IsUserSignedInUseCase {
    fun execute(): Single<Boolean>
}
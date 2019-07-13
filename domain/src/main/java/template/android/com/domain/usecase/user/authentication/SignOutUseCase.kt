package template.android.com.domain.usecase.user.authentication

import io.reactivex.Completable


interface SignOutUseCase {
    fun execute(): Completable
}
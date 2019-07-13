package template.android.com.domain.usecase.user.authentication

import io.reactivex.Completable
import template.android.com.domain.repository.UserRepository

// TODO: repository is getting data, so maybe use another class to sign user out?
class SignOutUseCaseImpl (private val userRepository: UserRepository) : SignOutUseCase {
    override fun execute(): Completable {
        return userRepository.signOutCurrentUser()
    }
}
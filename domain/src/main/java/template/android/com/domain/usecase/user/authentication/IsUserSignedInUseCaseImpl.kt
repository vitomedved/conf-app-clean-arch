package template.android.com.domain.usecase.user.authentication

import io.reactivex.Single
import template.android.com.domain.repository.UserRepository

class IsUserSignedInUseCaseImpl (private val userRepository: UserRepository) : IsUserSignedInUseCase {
    override fun execute(): Single<Boolean> {
        return userRepository.fetchIsUserSignedIn()
    }
}
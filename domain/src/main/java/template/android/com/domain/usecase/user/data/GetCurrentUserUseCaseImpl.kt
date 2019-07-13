package template.android.com.domain.usecase.user.data

import io.reactivex.Single
import template.android.com.domain.model.User
import template.android.com.domain.repository.UserRepository

class GetCurrentUserUseCaseImpl (private val userRepository: UserRepository) : GetCurrentUserUseCase {
    override fun execute(): Single<User> {
        return userRepository.fetchCurrentUser()
    }
}
package template.android.com.domain.usecase.user.data

import io.reactivex.Single
import template.android.com.domain.model.User

interface GetCurrentUserUseCase {
    fun execute(): Single<User>
}
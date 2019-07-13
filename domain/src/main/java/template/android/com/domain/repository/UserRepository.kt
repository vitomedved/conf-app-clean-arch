package template.android.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import template.android.com.domain.model.User

interface UserRepository{
    fun fetchIsUserSignedIn(): Single<Boolean>

    fun fetchCurrentUser(): Single<User>

    fun signOutCurrentUser(): Completable
}
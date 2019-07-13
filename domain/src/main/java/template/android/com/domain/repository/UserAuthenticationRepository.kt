package template.android.com.domain.repository

import io.reactivex.Single

interface UserAuthenticationRepository{
    fun fetchIsUserSignedIn(): Single<Boolean>
}
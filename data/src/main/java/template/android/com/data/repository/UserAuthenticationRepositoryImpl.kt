package template.android.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import template.android.com.domain.repository.UserAuthenticationRepository

class UserAuthenticationRepositoryImpl : UserAuthenticationRepository {
    override fun fetchIsUserSignedIn(): Single<Boolean> {
        return Single.fromCallable { FirebaseAuth.getInstance().currentUser != null }
    }
}
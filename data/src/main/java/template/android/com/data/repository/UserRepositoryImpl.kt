package template.android.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Single
import template.android.com.domain.model.User
import template.android.com.domain.repository.UserRepository
import template.android.com.domain.utils.string.StringUtils

class UserRepositoryImpl(private val stringUtils: StringUtils) : UserRepository {
    override fun fetchIsUserSignedIn(): Single<Boolean> {
        return Single.fromCallable { FirebaseAuth.getInstance().currentUser != null }
    }

    override fun fetchCurrentUser(): Single<User> {
        return Single.fromCallable {
            FirebaseAuth.getInstance()
                    .currentUser.let {
                User(stringUtils.itOrDefault(it?.uid, ""), stringUtils.itOrDefault(it?.email, ""))
            }
        }
    }

    override fun signOutCurrentUser(): Completable {
        return Completable.fromAction { FirebaseAuth.getInstance().signOut() }
    }
}
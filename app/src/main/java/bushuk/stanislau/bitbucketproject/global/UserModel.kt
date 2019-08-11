package bushuk.stanislau.bitbucketproject.global

import android.annotation.SuppressLint
import bushuk.stanislau.bitbucketproject.presentation.auth.IAccessRepository
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class UserModel @Inject constructor(
        authLoginRepository: IAccessRepository,
        tokenCache: ITokenCache)
    : IUserModel {


    override val user: BehaviorSubject<User> = BehaviorSubject.create()

    override fun setUser(user: User) {
        this.user.onNext(user)
    }

    override fun setNewUser(userName: String) {
        user.value!!.username = userName
    }

    init {
        if (tokenCache.accessToken != null) {
            authLoginRepository.getMyUser().subscribeOn(Schedulers.io())
                    .subscribe({
                        user.onNext(it)
                    }, {
                        Timber.e(it.message)
                    })
        }
    }

}

interface IUserModel {
    fun setNewUser(userName: String)

    fun setUser(user: User)

    val user: Observable<User>
}
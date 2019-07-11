package bushuk.stanislau.bitbucketproject.global

import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class UserModel @Inject constructor(val api: Api,
                                    tokenPreferencesUtil: SharedPreferencesUtil) : IUserModel {



    val user: BehaviorSubject<User> = BehaviorSubject.create()

    override fun setUser(user: User) {
        this.user.onNext(user)
    }

    override fun setNewUser(userName: String) {
        user.value!!.username = userName
    }

    init {
        if (tokenPreferencesUtil.getToken() != null) {
            api.myUser().subscribeOn(Schedulers.io())
                    .doOnSuccess { user.onNext(it) }
                    .onErrorReturn { null }
                    .subscribe()
        }
    }

}

interface IUserModel {
    fun setNewUser(userName: String)

    fun setUser(user: User)
}
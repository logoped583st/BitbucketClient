package bushuk.stanislau.bitbucketproject.global

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class UserModel {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var tokenPreferencesUtil: SharedPreferencesUtil

    val user: BehaviorSubject<User> = BehaviorSubject.create()

    init {
        App.component.inject(this)

        if (tokenPreferencesUtil.getToken() != null) {
            api.myUser().subscribeOn(Schedulers.io()).doOnSuccess { user.onNext(it) }
                    .onErrorReturn { null }
                    .subscribe()
        }
    }

}
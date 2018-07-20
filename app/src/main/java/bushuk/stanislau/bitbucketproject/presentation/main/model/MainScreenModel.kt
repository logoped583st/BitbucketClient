package bushuk.stanislau.bitbucketproject.presentation.main.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainScreenModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    private var observable: ConnectableObservable<User>? = null
    fun getUser(): ConnectableObservable<User> {
        return if (observable == null) {
            this.observable = api.myUser()
                    .subscribeOn(Schedulers.io())
                    .share()
                    .replay()
            this.observable!!
        } else {
            this.observable!!
        }
    }


}
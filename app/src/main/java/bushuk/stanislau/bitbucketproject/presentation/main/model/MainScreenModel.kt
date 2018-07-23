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

    fun getUser(): ConnectableObservable<User> {
        return api.myUser()
                .subscribeOn(Schedulers.io())
                .share()
                .replay()
    }
}


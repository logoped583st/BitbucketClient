package bushuk.stanislau.bitbucketproject.presentation.main.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject


class MainScreenModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    fun getUserToken(): Single<User> = api.myUser()

    fun getUserBaseAuth(): Single<User> {
        return api.myUserBaseAuth()
    }

}
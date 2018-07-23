package bushuk.stanislau.bitbucketproject.presentation.baseAuth.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthLoginModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    fun authSuccessful(): Single<User> = api.myUser()
}
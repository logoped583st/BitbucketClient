package bushuk.stanislau.bitbucketproject.presentation.main.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.pojo.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MainScreenModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    fun getUser(token: String): Single<User> = api.myUser(token)

}
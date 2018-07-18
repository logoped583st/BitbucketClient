package bushuk.stanislau.bitbucketproject.presentation.baseAuth.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import io.reactivex.Completable
import javax.inject.Inject

class AuthLoginModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    fun authSuccessful(): Completable = api.authSuccessful()
}
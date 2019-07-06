package bushuk.stanislau.bitbucketproject.presentation.auth

import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class AuthLoginModel @Inject constructor(val api: Api) : AuthProtocol.AuthLoginRepository {

    override fun authSuccessful(): Single<User> = api.myUser()
}


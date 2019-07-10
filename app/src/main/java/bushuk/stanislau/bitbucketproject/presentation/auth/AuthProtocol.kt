package bushuk.stanislau.bitbucketproject.presentation.auth

import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single

interface AuthProtocol {

    interface IAuthLoginRepository {
        fun authSuccessful(): Single<User>
    }

}
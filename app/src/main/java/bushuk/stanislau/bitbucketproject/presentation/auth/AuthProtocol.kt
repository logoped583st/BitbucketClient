package bushuk.stanislau.bitbucketproject.presentation.auth

import bushuk.stanislau.bitbucketproject.IBaseLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single

interface AuthProtocol {

    interface AuthLoginRepository {
        fun authSuccessful(): Single<User>
    }

    interface IAuthLogin<Response> : IBaseLoadingViewModel<Response> {
        fun getUserBaseAuth(login: String, password: String)

        fun navigateToBrowser()
    }
}
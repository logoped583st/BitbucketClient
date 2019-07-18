package bushuk.stanislau.bitbucketproject.presentation.auth

import android.util.Base64
import androidx.lifecycle.LiveData
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.Cicerones
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.presentation.base.LoadingViewModel
import bushuk.stanislau.bitbucketproject.presentation.base.addDisposable
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.extensions.applyDefaultSchedulers
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import timber.log.Timber
import javax.inject.Inject

class AuthLoginViewModel @Inject constructor(
        private val tokenPreferences: ISharedPreferencesUtil,
        routerFactory: CiceroneFactory,
        private val userModel: IUserModel,
        private val authLoginModel: AuthLoginRepository
) : LoadingViewModel<User>() {

    private val router = routerFactory.provideCicerone(Cicerones.GLOBAL).router

    override val state: LiveData<LoadingStateSealed<User, CustomExceptions>>
        get() = super.state

    fun getUserBaseAuth(login: String, password: String) {
        val credentials = "$login:$password"
        val basic: String = "Basic " + Base64.encodeToString(credentials
                .toByteArray(Charsets.ISO_8859_1), Base64.NO_WRAP)
        tokenPreferences.setToken(basic)


        addDisposable(authLoginModel.authSuccessful()
                .applyDefaultSchedulers()
                .loadingSubscriber(
                        {
                            userModel.setUser(it)
                            router.newRootScreen(ScreensNavigator.MainScreen())
                        },
                        {
                            Timber.e(it.message)
                        }
                ))
    }

    fun navigateToBrowser() {
        router.navigateTo(ScreensNavigator.WebLoginScreen())
    }

}



package bushuk.stanislau.bitbucketproject.presentation.auth

import android.util.Base64
import android.util.Base64.NO_WRAP
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.Cicerones
import bushuk.stanislau.bitbucketproject.global.ITokenCache
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.presentation.base.BaseLoadingViewModel
import bushuk.stanislau.bitbucketproject.presentation.base.addDisposable
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.extensions.applyDefaultSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AuthLoginViewModel @Inject constructor(
        private val tokenCache: ITokenCache,
        routerFactory: CiceroneFactory,
        private val userModel: IUserModel,
        private val authLoginRepository: IAccessRepository
) : BaseLoadingViewModel<User>() {

    private val router = routerFactory.provideCicerone(Cicerones.GLOBAL).router

    fun getUserBaseAuth(login: String, password: String) {
        val credentials = "$login:$password"
        val basic = "${Constants.TokenTypes.BASIC.type} ${Base64.encodeToString(credentials
                .toByteArray(Charsets.ISO_8859_1), NO_WRAP)}"
        tokenCache.accessToken = basic

        addDisposable(authLoginRepository.authSuccessful()
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

    fun getAccessToken(code: String) {
        addDisposable(authLoginRepository.getOauthToken(code)
                .applyDefaultSchedulers()
                .flatMap {
                    tokenCache.accessToken = "${Constants.TokenTypes.BEARER.type} ${it.accessToken}"
                    tokenCache.refreshToken = it.refreshToken
                    authLoginRepository.authSuccessful().subscribeOn(Schedulers.io())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userModel.setUser(it)
                    router.newRootScreen(ScreensNavigator.MainScreen())
                }, {
                    Timber.e(it.message)
                })
        )
    }

    fun navigateToBrowser() {
        router.navigateTo(ScreensNavigator.WebLoginScreen())
    }


}



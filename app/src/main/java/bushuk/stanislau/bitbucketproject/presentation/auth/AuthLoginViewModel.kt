package bushuk.stanislau.bitbucketproject.presentation.auth

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bushuk.stanislau.bitbucketproject.BaseLoadingViewModel
import bushuk.stanislau.bitbucketproject.IBaseLoadingViewModel
import bushuk.stanislau.bitbucketproject.addDisposable
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.extensions.applyDefaultSchedulers
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AuthLoginViewModel @Inject constructor(
        val tokenPreferences: SharedPreferencesUtil,
        val router: Router,
        val userModel: UserModel,
        val authLoginModel: AuthLoginModel
) : BaseLoadingViewModel<User>(), AuthProtocol.IAuthLogin<User> {


    override val state: LiveData<LoadingState.LoadingStateSealed<User, CustomExceptions>>
        get() = super.state()
    val snackBarAction: MutableLiveData<String> = MutableLiveData()


    override fun getUserBaseAuth(login: String, password: String) {
        val credentials = "$login:$password"
        val basic: String = "Basic " + Base64.encodeToString(credentials
                .toByteArray(Charsets.ISO_8859_1), Base64.NO_WRAP)
        tokenPreferences.setToken(basic)


        addDisposable(authLoginModel.authSuccessful()
                .applyDefaultSchedulers()
                .loadingSubscriber(
                        {
                            userModel.user.onNext(it)
                            // router.newRootScreen(Screens.MAIN_SCREEN)
                        },
                        {
                            if (it is HttpException && it.code() == 401) {
                                snackBarAction.postValue("Wrong login or password")
                            }else{
                                snackBarAction.postValue("Something going wrong")
                            }
                            tokenPreferences.clearToken()
                        }
                ))
    }

    override fun navigateToBrowser() {
        router.newChain(ScreensNavigator.WebLoginScreen())
    }

}



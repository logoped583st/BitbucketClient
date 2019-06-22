package bushuk.stanislau.bitbucketproject.presentation.auth

import android.util.Base64
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AuthLoginViewModel @Inject constructor(
        val tokenPreferences: SharedPreferencesUtil,
        val router: Router,
        val userModel: UserModel,
        val authLoginModel: AuthLoginModel

) : ViewModel() {


    val clickableSendButton: MutableLiveData<Boolean> = MutableLiveData()

    val snackBarAction: MutableLiveData<String> = MutableLiveData()

    init {
        clickableSendButton.postValue(true)
        App.component.inject(this)
    }


    fun getUserBaseAuth(login: String, password: String) {
        clickableSendButton.postValue(false)
        val credentials = "$login:$password"
        val basic: String = "Basic " + Base64.encodeToString(credentials
                .toByteArray(Charsets.ISO_8859_1), Base64.NO_WRAP)
        tokenPreferences.setToken(basic)


        authLoginModel.authSuccessful().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            userModel.user.onNext(it)
                           // router.newRootScreen(Screens.MAIN_SCREEN)
                        },
                        {
                            tokenPreferences.clearToken()
                            clickableSendButton.postValue(true)
                            snackBarAction.postValue("Wrong login or password")
                        }
                )
    }

    fun navigateToBrowser() {
//        router.setResultListener(Constants.GET_TOKEN_BROWSER, this)
//        router.navigateTo(Screens.LOGIN_SCREEN)
    }

}
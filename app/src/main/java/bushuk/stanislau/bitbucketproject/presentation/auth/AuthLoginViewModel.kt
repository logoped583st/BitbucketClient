package bushuk.stanislau.bitbucketproject.presentation.auth

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Base64
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.di.modules.global.RetrofitModule
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.result.ResultListener
import javax.inject.Inject

class AuthLoginViewModel : ViewModel(), ResultListener {

    override fun onResult(resultData: Any?) {
        if (resultData == RESULT_OK) {
            router.newRootScreen(Screens.MAIN_SCREEN)
        }
    }

    @Inject
    lateinit var tokenPreferences: SharedPreferencesUtil

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var authLoginModel: AuthLoginModel

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
                            router.newRootScreen(Screens.MAIN_SCREEN)
                        },
                        {
                            tokenPreferences.clearToken()
                            clickableSendButton.postValue(true)
                            snackBarAction.postValue("Wrong login or password")
                        }
                )
    }

    fun navigateToBrowser() {
        router.setResultListener(Constants.GET_TOKEN_BROWSER, this)
        router.navigateTo(Screens.LOGIN_SCREEN)
    }

}
package bushuk.stanislau.bitbucketproject.presentation.baseAuth.viewModel

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Base64
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.di.modules.RetrofitModule
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.result.ResultListener
import timber.log.Timber
import javax.inject.Inject

class AuthLoginViewModel : ViewModel(), ResultListener {

    override fun onResult(resultData: Any?) {
        if (resultData == RESULT_OK) {
            RetrofitModule().getApi()
            router.newRootScreen(Screens.MAIN_SCREEN)
        }
    }

    @Inject
    lateinit var tokenPreferences: SharedPreferencesUtil

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var authLoginModel: AuthLoginModel

    private val clickableSendButton: MutableLiveData<Boolean> = MutableLiveData()

    private val snackBarAction: MutableLiveData<String> = MutableLiveData()

    fun getClickableSendButton(): MutableLiveData<Boolean> = clickableSendButton

    fun getSnackBarAction():MutableLiveData<String> = snackBarAction

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
        RetrofitModule().getApi()
        authLoginModel.authSuccessful().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { router.newRootScreen(Screens.MAIN_SCREEN) }
                .doOnError {
                    Timber.e("ERROR")
                    tokenPreferences.clearToken()
                    clickableSendButton.postValue(true)
                    snackBarAction.postValue("Wrong login or password")
                }
                .onErrorComplete()
                .subscribe()
    }

    fun navigateToBrowser() {
        router.setResultListener(Constants.GET_TOKEN_BROWSER, this)
        router.navigateTo(Screens.LOGIN_SCREEN)
    }

}
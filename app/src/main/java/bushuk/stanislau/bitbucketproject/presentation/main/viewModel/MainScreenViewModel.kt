package bushuk.stanislau.bitbucketproject.presentation.main.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.pojo.User
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {

    @Inject
    lateinit var mainScreenModel: MainScreenModel

    @Inject
    lateinit var tokenPreferences: SharedPreferencesUtil

    private var user: MutableLiveData<User> = MutableLiveData()

    fun getUser(): MutableLiveData<User> = user


    init {
        App.component.inject(this)

        mainScreenModel.getUserToken().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    user.postValue(it)
                }
                .doOnError { Timber.e(it) }//TODO {catch error, add to Room}
                .subscribe()

    }


}
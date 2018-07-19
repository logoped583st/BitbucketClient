package bushuk.stanislau.bitbucketproject.presentation.main.viewModel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {


    @Inject
    lateinit var mainScreenModel: MainScreenModel

    @Inject
    lateinit var tokenPreferences: SharedPreferencesUtil

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var router: Router

    private var user: MutableLiveData<User> = MutableLiveData()

    fun getUser(): MutableLiveData<User> = user


    init {

        App.component.inject(this)
        Timber.e("INIT")

        mainScreenModel.getUser().subscribeOn(Schedulers.io())
                .map {
                    appDatabase.userDao().insertUser(it)
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    user.postValue(it)
                    router.replaceScreen(Screens.REPOSITORIES_SCREEN)
                }
                .doOnError { router.newRootScreen(Screens.LOGIN_SCREEN) }
                .subscribe()


    }


}
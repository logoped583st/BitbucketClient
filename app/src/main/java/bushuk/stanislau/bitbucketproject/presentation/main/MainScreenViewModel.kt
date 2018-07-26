package bushuk.stanislau.bitbucketproject.presentation.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
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

    @Inject
    lateinit var userModel: UserModel

    val toolbarTitle: MutableLiveData<String> = MutableLiveData()

    val user: MutableLiveData<User> = MutableLiveData()

    init {
        App.component.inject(this)
        Timber.e("INIT" + mainScreenModel.hashCode())

        userModel.user
                .subscribeOn(Schedulers.io())
                .map {
                    appDatabase.userDao().insertUser(it)
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    user.postValue(it)
                }

        drawerNavigation(Screens.REPOSITORIES_SCREEN,App.resourcesApp.getString(R.string.toolbar_title_repository))
    }

    fun drawerNavigation(screenKey: String, toolbarTitle:String) {
        this.toolbarTitle.postValue(toolbarTitle)
        router.replaceScreen(screenKey)
    }
}
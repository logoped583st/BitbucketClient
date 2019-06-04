package bushuk.stanislau.bitbucketproject.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.constants.Screens
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
    lateinit var tokenPreferences: SharedPreferencesUtil

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userModel: UserModel

    val toolbarTitle: MutableLiveData<String> = MutableLiveData()

    val user: MutableLiveData<User> = MutableLiveData()

    init {
        App.component.inject(this)

        userModel.user
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    user.postValue(it)
                }

        drawerNavigation(Screens.REPOSITORIES_SCREEN,App.resourcesApp.getString(R.string.toolbar_title_repository))
    }

    fun drawerNavigation(screenKey: String, toolbarTitle:String) {
        this.toolbarTitle.postValue(toolbarTitle)
        //router.newRootScreen(screenKey)
    }
}
package bushuk.stanislau.bitbucketproject.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
        val tokenPreferences: ISharedPreferencesUtil,
        @DrawerScope
        val router: Router,
        val userModel: UserModel
) : ViewModel() {

    val toolbarTitle: MutableLiveData<String> = MutableLiveData()

    val user: MutableLiveData<User> = MutableLiveData()

    init {
        userModel.user
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    user.postValue(it)
                }
        //drawerNavigation(ScreensNavigator.RepositoriesScreen(), App.resourcesApp.getString(R.string.toolbar_title_repository))
    }

    fun drawerNavigation(screen: SupportAppScreen, toolbarTitle: String) {
        this.toolbarTitle.postValue(toolbarTitle)
        router.newChain(screen)
    }
}
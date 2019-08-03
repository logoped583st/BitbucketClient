package bushuk.stanislau.bitbucketproject.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.Cicerones
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
        routerFactory: CiceroneFactory,
        userModel: UserModel
) : ViewModel() {


    private val router = routerFactory.provideCicerone(Cicerones.DRAWER).router

    val toolbarTitle: MutableLiveData<String> = MutableLiveData()

    val user: MutableLiveData<User> = MutableLiveData()

    private var currentScreen: NavigateTo? = null

    init {
        userModel.user
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    user.postValue(it)
                }

        drawerNavigation(NavigateTo.REPOSITORIES)
    }


    fun drawerNavigation(screen: NavigateTo) {
        if (screen != currentScreen) {
            currentScreen = screen
            this.toolbarTitle.postValue(screen.title)
            router.replaceScreen(screen.screen)
        }
    }


    enum class NavigateTo(val screen: SupportAppScreen, val title: String) {
        REPOSITORIES(ScreensNavigator.RepositoriesScreen(), App.resourcesApp.getString(R.string.toolbar_title_repository)),
        TEAMS(ScreensNavigator.TeamsScreen(), App.resourcesApp.getString(R.string.toolbar_title_teams)),
        SNIPPETS(ScreensNavigator.SnippetsScreen(), App.resourcesApp.getString(R.string.toolbar_title_snippets));
    }
}
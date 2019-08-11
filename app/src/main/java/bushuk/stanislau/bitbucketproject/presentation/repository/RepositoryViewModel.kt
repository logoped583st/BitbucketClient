package bushuk.stanislau.bitbucketproject.presentation.repository

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class RepositoryViewModel : ViewModel() {

    val cicerone: Cicerone<Router> = Cicerone.create()

    private val localRouter = cicerone.router!!

    @Inject
    lateinit var repositoryModel: RepositoryModel

    //@Inject
  //  lateinit var router: Router

    @Inject
    lateinit var userModel: IUserModel

    private var tabSelected: String = Screens.CODE_SCREEN


    val repository: MutableLiveData<Repository> = MutableLiveData()

    private lateinit var userMe: User

    init {
        //App.component.inject(this)
        repositoryModel.repository.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    repository.postValue(it)
                }, {
                    Timber.e(it.message)
                })

        userModel.user.subscribe {
            userMe = it.copy()
        }
    }


    fun exitFromFragment() {
        //router.exit()
    }

    override fun onCleared() {
        userModel.setUser(userMe)
        super.onCleared()
    }


    fun initView() {
       // localRouter.replaceScreen(tabSelected)
    }

    fun tabRouting(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true

        when (menuItem.itemId) {
            R.id.repository_code_menu -> {
                tabSelected = Screens.CODE_SCREEN
               // localRouter.replaceScreen(Screens.CODE_SCREEN)
            }

            R.id.repository_pullrequests_menu -> {
                tabSelected = Screens.PULL_REQUESTS_SCREEN

               // localRouter.replaceScreen(Screens.PULL_REQUESTS_SCREEN)
            }

            R.id.repository_watchers_menu -> {
                tabSelected = Screens.WATCHERS_SCREEN
              //  localRouter.replaceScreen(Screens.WATCHERS_SCREEN)
            }
        }

        return false
    }

}
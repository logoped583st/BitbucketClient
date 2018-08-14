package bushuk.stanislau.bitbucketproject.presentation.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.MenuItem
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class RepositoryViewModel : ViewModel() {

    val cicerone: Cicerone<Router> = Cicerone.create()

    val router = cicerone.router!!

    @Inject
    lateinit var repositoryModel: RepositoryModel

    val repository: MutableLiveData<Repository> = MutableLiveData()

    init {
        App.component.inject(this)
        repositoryModel.repository.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    repository.postValue(it)
                }, {
                    Timber.e(it.message)
                })
    }

    fun exitFromFragment() {
        router.exit()
    }

    fun initView() {
        router.replaceScreen(Screens.CODE_SCREEN)
    }

    fun tabRouting(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true

        when (menuItem.itemId) {
            R.id.repository_code_menu -> router.replaceScreen(Screens.CODE_SCREEN)

            R.id.repository_pullrequests_menu -> router.replaceScreen(Screens.PULL_REQUESTS_SCREEN)

            R.id.repository_watchers_menu -> router.replaceScreen(Screens.WATCHERS_SCREEN)
        }

        return false
    }

}
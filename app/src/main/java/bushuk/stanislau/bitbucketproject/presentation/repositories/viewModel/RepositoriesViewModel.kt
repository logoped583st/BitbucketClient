package bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesModel
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesModel: RepositoriesModel

    @Inject
    lateinit var appDatabase: AppDatabase
//    var repositories : MutableLiveData<>

    init {
        App.component.inject(this)

        appDatabase.userDao().getMeUser().subscribeOn(Schedulers.io())
                .doOnSuccess {
                    Timber.e(it.display_name)
                    repositoriesModel.getOwnRepositories(it.username).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSuccess { Timber.e(it.values[0].name) }
                            .subscribe()
                }
                .subscribe()

    }


}
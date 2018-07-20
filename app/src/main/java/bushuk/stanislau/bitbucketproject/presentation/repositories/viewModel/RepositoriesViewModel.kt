package bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesModel
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesModel: RepositoriesModel

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var mainScreenModel : MainScreenModel

    var repositories: MutableLiveData<MutableList<Repository>> = MutableLiveData()


    init {
        App.component.inject(this)
        Timber.e("INIT" + mainScreenModel.hashCode())


        mainScreenModel.getUser()
                .switchMapSingle { it -> repositoriesModel.getOwnRepositories(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //user
                    Timber.e(it.values[0].name)
                    repositories.postValue(it.values)
                },{
                    //do on erroor
                })



        mainScreenModel.getUser().connect()



//            appDatabase.userDao().getMeUser().subscribeOn(Schedulers.io())
//
//                    .flatMap { repositoriesModel.getOwnRepositories(it.username).subscribeOn(Schedulers.io()) }
//                    .doOnSuccess {
//                        Timber.e("POST VALUE")
//                        repositories.postValue(it.values)
//                    }
    }
}
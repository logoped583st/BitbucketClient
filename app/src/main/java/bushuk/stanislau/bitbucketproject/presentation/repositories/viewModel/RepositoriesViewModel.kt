package bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesModel
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesModel: RepositoriesModel

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var mainScreenModel: MainScreenModel

    private var repositories: MutableLiveData<MutableList<Repository>> = MutableLiveData()

    private var loading: MutableLiveData<Int> = MutableLiveData()

    fun getRepositories(): MutableLiveData<MutableList<Repository>> = repositories


    fun getLoading() = loading

    init {
        loading.postValue(View.VISIBLE)

        App.component.inject(this)
        Timber.e("INIT" + mainScreenModel.hashCode())


        mainScreenModel.getUser()
                .autoConnect()
                .switchMapSingle {
                    repositoriesModel.getOwnRepositories(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //user
                    Timber.e(it.values[0].name)
                    repositories.postValue(it.values)
                    loading.postValue(View.GONE)
                }, {
                    loading.postValue(View.GONE)
                    //do on erroor
                })



    }
}
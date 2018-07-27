package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepositoriesDataSource : PageKeyedDataSource<String, Repository>() {


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    val loading: MutableLiveData<Int> = MutableLiveData()

    val noRepositories: MutableLiveData<Int> = MutableLiveData()


    init {
        App.component.inject(this)
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        api.getReposNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ callback.onResult(it.values,it.next) },
                        {
                            Timber.e(it.message)
                        })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Repository>) {
        loading.postValue(View.VISIBLE)
        userModel.user.subscribeOn(Schedulers.io())
                .switchMapSingle { api.getRepos("tutorials") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.size==0){
                        noRepositories.postValue(View.VISIBLE)
                    }
                    callback.onResult(it.values, it.previous, it.next)
                    loading.postValue(View.GONE)
                }, {
                    Timber.e(it.message)
                })
    }


}
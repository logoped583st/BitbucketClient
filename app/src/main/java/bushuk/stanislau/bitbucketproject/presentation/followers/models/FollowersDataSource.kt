package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FollowersDataSource : PageKeyedDataSource<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    val noFollowers: MutableLiveData<Int> = MutableLiveData()

    val loading:MutableLiveData<Int> = MutableLiveData()

    init {
        loading.postValue(View.VISIBLE)
        App.component.inject(this)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        userModel.user.subscribeOn(Schedulers.io())
                .switchMapSingle { api.getFollowers(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            loading.postValue(View.INVISIBLE)
                            if (it.size == 0) {
                                noFollowers.postValue(View.VISIBLE)
                            }

                            callback.onResult(it.values, it.previous, it.next)
                        },
                        {
                            Timber.e(it.message)
                        })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        api.getFollowersNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            loading.postValue(View.INVISIBLE)
                            callback.onResult(it.values, it.next)
                        },
                        {
                            Timber.e(it.message)
                        })
    }
}
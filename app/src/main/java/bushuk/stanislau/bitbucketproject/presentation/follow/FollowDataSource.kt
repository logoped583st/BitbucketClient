package bushuk.stanislau.bitbucketproject.presentation.follow

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

abstract class FollowDataSource : PageKeyedDataSource<String, User>() {


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    abstract val single: Single<Followers>

    @Inject
    lateinit var repositoryModel: RepositoryModel

    val loading: MutableLiveData<Int> = MutableLiveData()

    val noFollowers: MutableLiveData<Int> = MutableLiveData()

    val errorText: MutableLiveData<String> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun invalidate() {
        disposable.clear()
        super.invalidate()
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        noFollowers.postValue(View.INVISIBLE)
        loading.postValue(View.VISIBLE)

        disposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.size == 0) {
                                noFollowers.postValue(View.VISIBLE)
                                doOnEmpty()
                            }

                            loading.postValue(View.INVISIBLE)
                            callback.onResult(it.values, it.previous, it.next)
                        },
                        {
                            Timber.e(it.message)
                        }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        disposable.add(api.getFollowersNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            callback.onResult(it.values, it.next)
                        },
                        {
                            Timber.e(it.message)
                        }))
    }


    abstract fun doOnEmpty()
}
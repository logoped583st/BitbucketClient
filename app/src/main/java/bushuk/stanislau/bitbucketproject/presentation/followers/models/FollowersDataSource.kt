package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FollowersDataSource : PageKeyedDataSource<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var loadingModel: LoadingModel

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun invalidate() {
        disposable.clear()
        super.invalidate()
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        loadingModel.noFollowers.postValue(View.INVISIBLE)
        loadingModel.loading.postValue(View.VISIBLE)

        disposable.add(userModel.user.subscribeOn(Schedulers.io())
                .switchMapSingle { api.getFollowers(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.size == 0) {
                                loadingModel.noFollowers.postValue(View.VISIBLE)
                                loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.followers_screen_no_followers))
                            }

                            loadingModel.loading.postValue(View.INVISIBLE)
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
}
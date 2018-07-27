package bushuk.stanislau.bitbucketproject.presentation.following.models

import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.followers.models.FollowModel
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FollowingDataSource : PageKeyedDataSource<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var followModel: FollowModel

    init {
        App.component.inject(this)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        api.getFollowingNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            callback.onResult(it.values, it.next)
                        },
                        {
                            Timber.e(it.message)
                        })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        followModel.noFollowers.postValue(View.INVISIBLE)
        followModel.loading.postValue(View.VISIBLE)

        userModel.user.subscribeOn(Schedulers.io())
                .switchMapSingle { api.getFollowing(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.size == 0) {
                                followModel.noFollowers.postValue(View.VISIBLE)
                                followModel.errorText.postValue(App.resourcesApp.getString(R.string.following_screen_no_following))
                            }

                            followModel.loading.postValue(View.INVISIBLE)
                            callback.onResult(it.values, it.previous, it.next)
                        },
                        {
                            Timber.e(it.message)
                        })
    }
}
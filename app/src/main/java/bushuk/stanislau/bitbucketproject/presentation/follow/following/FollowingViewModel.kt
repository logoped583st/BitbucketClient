package bushuk.stanislau.bitbucketproject.presentation.follow.following

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class FollowingViewModel : BaseFollowViewModel() {


    @Inject
    lateinit var _factory: FollowingDataSourceFactory

    init {
        App.component.inject(this)
    }

    override val dataSource: BaseDataSource<User, Followers>
        get() = _factory.followingDataSource

    override var factory: DataSource.Factory<String, User> = _factory


    override fun onCleared() {
        super.onCleared()
        _factory.followingDataSource .invalidate()
    }
}
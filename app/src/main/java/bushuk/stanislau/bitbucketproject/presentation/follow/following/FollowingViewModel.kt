package bushuk.stanislau.bitbucketproject.presentation.follow.following

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowingViewModel : BaseFollowViewModel() {

    @Inject
    lateinit var _factory: FollowingDataSourceFactory

    init {
        App.component.inject(this)
    }

    override var factory: DataSource.Factory<String, User> = _factory

    override fun onCleared() {
        _factory.followingDataSource.invalidate()
        super.onCleared()
    }
}
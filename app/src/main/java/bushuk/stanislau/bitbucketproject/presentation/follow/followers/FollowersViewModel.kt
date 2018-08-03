package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import timber.log.Timber
import javax.inject.Inject

class FollowersViewModel : BaseFollowViewModel() {

    @Inject
    lateinit var _factory: FollowersDataSourceFactory

    init {
        App.component.inject(this)
    }

    override var factory: DataSource.Factory<String, User> = _factory

}
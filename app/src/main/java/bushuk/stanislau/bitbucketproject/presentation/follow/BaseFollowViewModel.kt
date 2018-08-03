package bushuk.stanislau.bitbucketproject.presentation.follow

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.LoadingModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import javax.inject.Inject

abstract class BaseFollowViewModel : ViewModel() {

    @Inject
    lateinit var loadingModel: LoadingModel

    @Inject
    lateinit var followingDataSourceFactory: FollowingDataSourceFactory

    @Inject
    lateinit var followersDataSourceFactory: FollowersDataSourceFactory

    init {
        App.component.inject(this)
    }

}
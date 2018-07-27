package bushuk.stanislau.bitbucketproject.presentation.following

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.presentation.followers.models.FollowModel
import bushuk.stanislau.bitbucketproject.presentation.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowingViewModel : ViewModel() {

    @Inject
    lateinit var followModel: FollowModel

    @Inject
    lateinit var followingDataSourceFactory: FollowingDataSourceFactory

    init {
        App.component.inject(this)
    }

    val following: LiveData<PagedList<User>> = LivePagedListBuilder<String, User>(followingDataSourceFactory, Constants.listPagedConfig).build()
}
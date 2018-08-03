package bushuk.stanislau.bitbucketproject.presentation.follow.following

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.LoadingModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowingViewModel : BaseFollowViewModel() {

    val following: LiveData<PagedList<User>> = LivePagedListBuilder<String, User>(followingDataSourceFactory, Constants.listPagedConfig).build()

}
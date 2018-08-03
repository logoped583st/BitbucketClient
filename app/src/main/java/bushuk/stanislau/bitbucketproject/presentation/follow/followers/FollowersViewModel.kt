package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.LoadingModel
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowersViewModel : BaseFollowViewModel() {


    var followers: LiveData<PagedList<User>> = LivePagedListBuilder<String, User>(followersDataSourceFactory, Constants.listPagedConfig).build()


}
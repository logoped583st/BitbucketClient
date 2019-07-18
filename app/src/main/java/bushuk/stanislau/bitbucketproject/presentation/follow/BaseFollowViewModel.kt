package bushuk.stanislau.bitbucketproject.presentation.follow

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User

abstract class BaseFollowViewModel<Factory : DataSource.Factory<String, User>>(factory: Factory)
    : ListLoadingViewModel<Followers>(TODO()) {
    val followers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(factory, Constants.listPagedConfig).build() }
}
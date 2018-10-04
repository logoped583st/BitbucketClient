package bushuk.stanislau.bitbucketproject.presentation.follow

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseFollowViewModel<Factory : DataSource.Factory<String, User>>
(factory: Factory, source: BaseDataSource<User, Followers>)
    : LoadingViewModel<User, Followers, Factory>(factory, source) {
    val followers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(dataSourceFactory, Constants.listPagedConfig).build() }
}
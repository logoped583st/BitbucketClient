package bushuk.stanislau.bitbucketproject.presentation.follow.following

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions

class FollowingViewModel(factory: FollowingDataSourceFactory = FollowingDataSourceFactory())
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory)  {
    override val state: LiveData<LoadingState.LoadingStateSealed<Followers, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    //@Inject
  //  lateinit var router: Router

    init {
        //App.component.inject(this)
    }

    fun navigateToUserScreen(userName: User) {
        //router.navigateTo(Screens.USER_SCREEN, userName)
    }

    override fun onCleared() {
        super.onCleared()
      //  source.invalidate()
    }
}
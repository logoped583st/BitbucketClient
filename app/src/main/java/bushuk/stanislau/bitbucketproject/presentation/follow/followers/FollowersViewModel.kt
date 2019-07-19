package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowersViewModel
@Inject constructor(factory: FollowersDataSourceFactory)
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory) {


    //@Inject
  //  lateinit var router: Router

    init {
        //App.component.inject(this)
    }

    fun navigateToUserScreen(userName: User) {
       // router.navigateTo(Screens.USER_SCREEN, userName)
    }

    override fun onCleared() {
        super.onCleared()
        //source.invalidate()
    }


}
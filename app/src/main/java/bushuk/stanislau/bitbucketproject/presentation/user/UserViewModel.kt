package bushuk.stanislau.bitbucketproject.presentation.user

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class UserViewModel : ViewModel() {

    @Inject
    lateinit var userModel: IUserModel

    //@Inject
  //  lateinit var router: Router
    init {
        //App.component.inject(this)
    }

    private val userMe: User= userModel.user.blockingFirst().copy()

    override fun onCleared() {
        userModel.user.blockingFirst().username = userMe.username
        super.onCleared()
    }

    fun setUser(userName: String) {
        userModel.setNewUser(userName)
    }

    fun exitFromFragment(){
        //router.exit()
    }
}
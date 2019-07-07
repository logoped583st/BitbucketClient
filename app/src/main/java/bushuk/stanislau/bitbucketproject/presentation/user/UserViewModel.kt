package bushuk.stanislau.bitbucketproject.presentation.user

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class UserViewModel : ViewModel() {

    @Inject
    lateinit var userModel: UserModel

    //@Inject
  //  lateinit var router: Router
    init {
        //App.component.inject(this)
    }

    private val userMe: User= userModel.user.value!!.copy()

    override fun onCleared() {
        userModel.user.value!!.username = userMe.username
        super.onCleared()
    }

    fun setUser(userName: String) {
        userModel.setNewUser(userName)
    }

    fun exitFromFragment(){
        //router.exit()
    }
}
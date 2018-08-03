package bushuk.stanislau.bitbucketproject.presentation.user

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User

class UserViewModel : ViewModel() {

    init {
        App.component.inject(this)
    }
}
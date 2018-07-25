package bushuk.stanislau.bitbucketproject.presentation.followers

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App

class FollowersViewModel : ViewModel() {


    init {
        App.component.inject(this)
    }
}
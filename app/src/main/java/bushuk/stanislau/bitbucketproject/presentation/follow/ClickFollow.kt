package bushuk.stanislau.bitbucketproject.presentation.follow

import android.view.View
import bushuk.stanislau.bitbucketproject.room.user.User

interface ClickFollow<T> {
    fun onClickItem(view: View, data: T)
}
package bushuk.stanislau.bitbucketproject.presentation.follow

import android.view.View

interface ClickFollow<T> {
    fun onClickItem(view: View, data: T)
}
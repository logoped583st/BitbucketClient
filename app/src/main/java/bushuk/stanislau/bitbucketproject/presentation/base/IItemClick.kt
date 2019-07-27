package bushuk.stanislau.bitbucketproject.presentation.base

import android.view.View

interface IItemClick<T> {
    fun onClickItem(view: View, data: T)
}
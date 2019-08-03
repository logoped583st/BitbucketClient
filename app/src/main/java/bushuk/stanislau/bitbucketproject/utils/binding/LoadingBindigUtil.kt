package bushuk.stanislau.bitbucketproject.utils.binding

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.views.LoadingView

@BindingAdapter("bindClickable")
fun Button.bindClickable(state: LoadingStateSealed<*, *>?) {
    when (state) {
        is LoadingStateSealed.Loading -> {
            this.isClickable = false
            this.isFocusable = false
        }
        is LoadingStateSealed.Data<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }
        is LoadingStateSealed.Error<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }

        is LoadingStateSealed.Start<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }
    }
}

@BindingAdapter("loading")
fun ProgressBar.visibility(visibility: LoadingStateSealed<*, *>?) {

    visibility?.apply {
        this@visibility.visibility(loadingStateVisibility())
    }
}

fun LoadingStateSealed<*, *>.loadingStateVisibility(): Boolean {
    return when (this) {
        is LoadingStateSealed.Start -> false
        is LoadingStateSealed.Loading -> true
        is LoadingStateSealed.Data -> false
        is LoadingStateSealed.Error -> false
        is LoadingStateSealed.Refresh -> false
        is LoadingStateSealed.LoadingNextItems -> false
    }
}

fun View.visibility(boolean: Boolean?) {
    visibility = if (boolean == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("loading", "root")
fun LoadingView.visibility(visibility: LoadingStateSealed<*, *>?, rootView: ViewGroup?) {
    rootView?.apply {
        if (visibility?.loadingStateVisibility() == true) {
            show(this)
        } else {
            hide(this)
        }
    }

}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refresh(visibility: LoadingStateSealed<*, *>?) {
    when (visibility) {
        is LoadingStateSealed.Data -> {
            isRefreshing = false
        }
    }
}



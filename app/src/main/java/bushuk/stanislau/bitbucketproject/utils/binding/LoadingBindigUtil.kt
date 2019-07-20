package bushuk.stanislau.bitbucketproject.utils.binding

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.views.LoadingView
import timber.log.Timber

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
    Timber.e(visibility.toString())
    rootView?.apply {
        if (visibility?.loadingStateVisibility() == true) {
            show(this)
        } else {
            hide(this)
        }
    }

}


@BindingAdapter("loading")
fun RecyclerView.loadingStateVisibility(visibility: LoadingStateSealed<*, *>?) {
    this.visibility = when (visibility) {
        is LoadingStateSealed.Start -> View.GONE
        is LoadingStateSealed.Loading -> View.GONE
        is LoadingStateSealed.Data -> View.VISIBLE
        is LoadingStateSealed.Error -> View.VISIBLE
        null -> View.GONE
    }
}


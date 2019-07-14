package bushuk.stanislau.bitbucketproject.utils.binding

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.global.LoadingState

@BindingAdapter("loading")
fun ProgressBar.visibility(visibility: LoadingState.LoadingStateSealed<*, *>?) {

    this.visibility = when (visibility) {
        is LoadingState.LoadingStateSealed.Start -> View.VISIBLE
        is LoadingState.LoadingStateSealed.Loading -> View.VISIBLE
        is LoadingState.LoadingStateSealed.Data -> View.GONE
        is LoadingState.LoadingStateSealed.Error -> View.GONE
        else -> View.GONE
    }

}
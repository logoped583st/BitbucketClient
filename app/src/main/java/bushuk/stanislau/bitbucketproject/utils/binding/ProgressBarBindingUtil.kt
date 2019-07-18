package bushuk.stanislau.bitbucketproject.utils.binding

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed

@BindingAdapter("loading")
fun ProgressBar.visibility(visibility: LoadingStateSealed<*, *>?) {

    this.visibility = when (visibility) {
        is LoadingStateSealed.Start -> View.VISIBLE
        is LoadingStateSealed.Loading -> View.VISIBLE
        is LoadingStateSealed.Data -> View.GONE
        is LoadingStateSealed.Error -> View.GONE
        else -> View.GONE
    }

}
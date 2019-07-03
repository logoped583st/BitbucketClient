package bushuk.stanislau.bitbucketproject.utils.binding

import android.widget.Button
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.global.LoadingState

@BindingAdapter("bindClickable")
fun Button.bindClickable(state: LoadingState.LoadingStateSealed<*, *>?) {
    when (state) {
        is LoadingState.LoadingStateSealed.Loading -> {
            this.isClickable = false
            this.isFocusable = false
        }
        is LoadingState.LoadingStateSealed.Data<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }
        is LoadingState.LoadingStateSealed.Error<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }

        is LoadingState.LoadingStateSealed.Start<*, *> -> {
            this.isClickable = true
            this.isFocusable = true
        }
    }
}
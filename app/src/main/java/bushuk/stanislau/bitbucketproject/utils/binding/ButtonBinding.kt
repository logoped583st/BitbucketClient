package bushuk.stanislau.bitbucketproject.utils.binding

import android.widget.Button
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed

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
package bushuk.stanislau.bitbucketproject.utils.binding

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ProgressBar

class ViewVisibilityUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("android:loadingStateVisibility")
        fun setVisibility(progressBar: ProgressBar, visibility: Int) {
            when (visibility) {
                View.GONE -> progressBar.visibility = View.GONE

                View.VISIBLE -> progressBar.visibility = View.VISIBLE
            }
        }
    }
}
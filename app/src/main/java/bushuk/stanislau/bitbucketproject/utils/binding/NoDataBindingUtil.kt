package bushuk.stanislau.bitbucketproject.utils.binding

import androidx.databinding.BindingAdapter
import android.view.View

class NoDataBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("android:loadingStateVisibility")
        fun error(view: View, visibility: Int?) {

            if (visibility == null) {
                view.visibility = View.INVISIBLE
            } else {
                view.visibility = visibility
            }
        }
    }
}
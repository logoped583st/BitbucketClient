package bushuk.stanislau.bitbucketproject.utils.binding

import android.databinding.BindingAdapter
import android.view.View

class NoDataBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("android:visibility")
        fun error(view: View, visibility: Int?) {

            if (visibility == null) {
                view.visibility = View.INVISIBLE
            } else {
                view.visibility = visibility
            }
        }
    }
}
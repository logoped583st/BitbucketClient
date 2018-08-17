package bushuk.stanislau.bitbucketproject.utils.binding

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView


class BottomNavigationBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("onNavigationItemSelected")
        fun setOnNavigationItemSelectedListener(
                view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener) {
            view.setOnNavigationItemSelectedListener(listener)
        }
    }

}
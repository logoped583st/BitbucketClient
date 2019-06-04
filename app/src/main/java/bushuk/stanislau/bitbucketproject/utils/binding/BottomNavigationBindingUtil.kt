package bushuk.stanislau.bitbucketproject.utils.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


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
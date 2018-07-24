package bushuk.stanislau.bitbucketproject.utils.binding

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.TextView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import javax.inject.Inject

class TextAccesBindingUtil {

    @Inject
    lateinit var applicationContext: Context


    companion object {

        @JvmStatic
        @BindingAdapter("android:text")
        fun accessText(textView: TextView, access: Boolean) {

            if (access) {
                textView.text = "Private"
            } else {
                textView.text = "Public"
            }
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun accessBackgroundColor(textView: TextView, access: Boolean) {
            if (access) {
                textView.setBackgroundColor(App.resourcesApp.getColor(R.color.colorAccent))
            } else {
                textView.setBackgroundColor(App.resourcesApp.getColor(R.color.colorPrimary))
            }
        }




    }
}
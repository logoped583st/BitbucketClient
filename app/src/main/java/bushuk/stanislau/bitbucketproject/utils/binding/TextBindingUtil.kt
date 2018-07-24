package bushuk.stanislau.bitbucketproject.utils.binding

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.widget.TextView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import java.text.SimpleDateFormat
import java.util.*

object TextBindingUtil {

    @JvmStatic
    @BindingAdapter("app:text")
    fun accessText(textView: TextView, string: String) {

        if (string.isEmpty()) {
            textView.text = "Description is empty"
            textView.setTextColor(App.resourcesApp.getColor(R.color.grey))
        } else {
            textView.text = string
        }
    }

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    @BindingAdapter("android:text")
    fun dateText(textView: TextView, date: Date) {
        val format = SimpleDateFormat("dd/MM/yyy")
        textView.text = format.format(date)
    }

}
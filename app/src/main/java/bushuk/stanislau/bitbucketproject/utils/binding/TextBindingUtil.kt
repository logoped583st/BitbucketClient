package bushuk.stanislau.bitbucketproject.utils.binding

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import br.tiagohm.markdownview.MarkdownView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import java.text.SimpleDateFormat
import java.util.*

class TextBindingUtil {

    companion object {

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

        @JvmStatic
        @BindingAdapter("app:text")
        fun markDownText(textView: MarkdownView, string: String) {
            if (string.isEmpty()) {
                textView.visibility = View.GONE
            } else {
                textView.loadMarkdown(string)

            }
        }

        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        @BindingAdapter("android:text")
        fun dateText(textView: TextView, date: Date) {
            val format = SimpleDateFormat("dd/MM/yyy")
            textView.text = format.format(date)
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("android:text")
        fun intText(textView: TextView, int: Int) {
            textView.text = '#' + int.toString()
        }

        @JvmStatic
        @BindingAdapter("pullRequestState")
        fun setState(textView: TextView, string: String?) {
            when (string) {
                "OPEN" -> {
                    textView.text = string
                    textView.setTextColor(App.resourcesApp.getColor(R.color.green))
                }

                "DECLINED" -> {
                    textView.text = string
                    textView.setTextColor(App.resourcesApp.getColor(R.color.black))
                }

                "MERGED" -> {
                    textView.text = string
                    textView.setTextColor(App.resourcesApp.getColor(R.color.colorAccent))
                }
            }
        }

        @JvmStatic
        @BindingAdapter("app:approveState")
        fun setTextApprove(textView: TextView, isApproved: Boolean) {
            if (isApproved) {
                textView.text = "UnApprove"
            } else if (!isApproved) {
                textView.text = "Approve"
            }
        }
    }
}
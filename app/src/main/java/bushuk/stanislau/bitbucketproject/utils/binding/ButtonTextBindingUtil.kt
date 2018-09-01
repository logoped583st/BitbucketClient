package bushuk.stanislau.bitbucketproject.utils.binding

import android.databinding.BindingAdapter
import android.widget.TextView
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestParticipants
import timber.log.Timber

class ButtonTextBindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter("app:approveCount")
        fun approveCount(textView: TextView, participants: List<PullRequestParticipants>?) {

            Timber.e(participants.toString())

            if (participants == null) {
                textView.text = "0"
            } else {
                var count = 0
                participants.forEach {
                    if (it.approved) {
                        count++
                    }
                }
                textView.text = count.toString()
            }
        }

        @JvmStatic
        @BindingAdapter("app:approveAction")
        fun approveAction(textView: TextView, boolean: Boolean) {
            try {
                val count: Int = textView.text.toString().toInt()

                if (boolean) {
                    textView.text = (count + 1).toString()
                } else {
                    textView.text = (count - 1).toString()
                }
            }catch (e:NumberFormatException){
                Timber.e("NUMBER FORMAT EXCEPTION")
            }
        }
    }
}
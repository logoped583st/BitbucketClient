package bushuk.stanislau.bitbucketproject.utils.binding

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import com.google.android.material.chip.Chip
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException

class TextAccessBindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter("android:text")
        fun accessText(chip: Chip, access: Boolean) {

            if (access) {
                chip.text = "Private"
            } else {
                chip.text = "Public"
            }
        }

        @JvmStatic
        @BindingAdapter("android:color")
        fun languageText(textView: TextView, string: String) {
            Timber.e(string)
            if (string != "") {
                val obj = JSONObject(loadJSONFromAsset())

                try {
                    textView.setTextColor(Color.parseColor(obj.getString(string)))
                } catch (e: Exception) {
                    textView.setTextColor(Color.parseColor("#000000"))
                }

            }
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun accessBackgroundColor(chip: Chip, access: Boolean) {
            if (access) {
                chip.setChipBackgroundColorResource(R.color.colorAccent)
            } else {
                chip.setChipBackgroundColorResource(R.color.colorPrimary)
            }
        }

        private fun loadJSONFromAsset(): String? {
            var json: String? = null
            try {
                val inputStream = App.resourcesApp.assets.open("colors.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }

}
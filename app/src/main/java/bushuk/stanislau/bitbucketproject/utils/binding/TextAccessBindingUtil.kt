package bushuk.stanislau.bitbucketproject.utils.binding

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Color
import android.widget.TextView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class TextAccessBindingUtil {

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
        @BindingAdapter("android:color")
        fun languageText(textView: TextView,string: String){
            Timber.e(string)
            if(string!="") {
                val obj = JSONObject(loadJSONFromAsset())

                try {
                    textView.setTextColor(Color.parseColor(obj.getString(string)))
                }catch (e:Exception){
                    textView.setTextColor(Color.parseColor("#000000"))
                }

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
package bushuk.stanislau.bitbucketproject.utils.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
//import bushuk.stanislau.bitbucketproject.GlideApp
import bushuk.stanislau.bitbucketproject.R

class ImageBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
//            GlideApp.with(imageView.context)
//                    .load(url)
//                    .centerCrop()
//                    .placeholder(R.color.white)
//                    .error(R.drawable.ic_teamblue)
//                    .into(imageView)
        }


        @JvmStatic
        @BindingAdapter("imageCodeUrl")
        fun codeImage(imageView: ImageView, url: String) {

            if (url == "commit_directory") {
//                GlideApp.with(imageView.context)
//                        .load(R.drawable.ic_folder_24dp)
//                        .centerCrop()
//                        .into(imageView)
            } else {
//                GlideApp.with(imageView.context)
//                        .load(R.drawable.ic_file_24dp)
//                        .centerCrop()
//                        .into(imageView)
            }
        }
    }
}
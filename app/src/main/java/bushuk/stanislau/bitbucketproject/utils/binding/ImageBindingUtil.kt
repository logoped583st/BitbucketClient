package bushuk.stanislau.bitbucketproject.utils.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
//import bushuk.stanislau.bitbucketproject.GlideApp
import bushuk.stanislau.bitbucketproject.R
import com.bumptech.glide.Glide

class ImageBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.color.white)
                    .error(R.drawable.ic_teamblue)
                    .into(imageView)
        }


        @JvmStatic
        @BindingAdapter("imageCodeUrl")
        fun codeImage(imageView: ImageView, url: String) {

            if (url == "commit_directory") {
                Glide.with(imageView.context)
                        .load(R.drawable.ic_folder_24dp)
                        .centerCrop()
                        .into(imageView)
            } else {
                Glide.with(imageView.context)
                        .load(R.drawable.ic_file_24dp)
                        .centerCrop()
                        .into(imageView)
            }
        }
    }
}
package bushuk.stanislau.bitbucketproject.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import bushuk.stanislau.bitbucketproject.GlideApp
import bushuk.stanislau.bitbucketproject.GlideAppModule
import bushuk.stanislau.bitbucketproject.R
import com.bumptech.glide.Glide

class ImageBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {


            GlideApp.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
        }
    }
}
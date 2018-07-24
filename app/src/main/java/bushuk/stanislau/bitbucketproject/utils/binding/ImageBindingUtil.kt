package bushuk.stanislau.bitbucketproject.utils.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import bushuk.stanislau.bitbucketproject.GlideApp
import bushuk.stanislau.bitbucketproject.R

class ImageBindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            GlideApp.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.color.white)
                    .error(R.drawable.logo)
                    .into(imageView)
        }
    }
}
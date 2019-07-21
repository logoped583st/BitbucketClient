package bushuk.stanislau.bitbucketproject.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import bushuk.stanislau.bitbucketproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import de.hdodenhof.circleimageview.CircleImageView


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
            .load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_teamblue)
            .into(this)
}


@BindingAdapter("imageCodeUrl")
fun ImageView.codeImage(url: String) {

    if (url == "commit_directory") {
        Glide.with(this.context)
                .load(R.drawable.ic_folder_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(this)
    } else {
        Glide.with(this.context)
                .load(R.drawable.ic_file_24dp)
                .centerCrop()
                .into(this)
    }
}

@BindingAdapter("repositoryImage")
fun CircleImageView.repositoryImage(url: String) {
    Glide.with(this.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.logo)
//            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}




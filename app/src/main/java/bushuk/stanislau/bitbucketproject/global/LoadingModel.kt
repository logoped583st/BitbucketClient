package bushuk.stanislau.bitbucketproject.global

import android.view.View

data class LoadingModel(
        var noInfo: Int = View.INVISIBLE,
        var loading: Int = View.VISIBLE,
        var errorText: String = ""
)
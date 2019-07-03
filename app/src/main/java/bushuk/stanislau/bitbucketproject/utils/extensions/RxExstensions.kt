package bushuk.stanislau.bitbucketproject.utils.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

fun <T> Single<T>.applyDefaultSchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Throwable.mapExceptions(): Exception {
    return when (this) {
        is HttpException -> {

            Exception("HTTP")
        }

        else -> {
            Exception("TEST")
        }

    }
}
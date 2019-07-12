package bushuk.stanislau.bitbucketproject.utils.extensions

import androidx.appcompat.widget.AppCompatSpinner
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.itemSelections
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException

fun <T> Single<T>.applyDefaultSchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyDefaultSchedulers(): Completable {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyDefaultSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.mapErrors(call: (error: CustomExceptions) -> Unit): Single<T> {

    return onErrorResumeNext { error ->
        val customError: CustomExceptions = when (error) {
            is SocketTimeoutException -> {
                CustomExceptions.SocketTimeOut
            }
            is HttpException -> {
                when (error.code()) {
                    400 -> CustomExceptions.BadBody
                    401 -> CustomExceptions.UnAuthorized
                    in 500..600 -> CustomExceptions.ServerError
                    else -> CustomExceptions.ANOTHER(error.message())
                }
            }
            else -> CustomExceptions.ANOTHER(error.message ?: "")
        }

        call(customError)


        return@onErrorResumeNext Single.error<T>(customError)
    }
}

fun RxAdapterView.spinner(spinner: AppCompatSpinner): Observable<String> {
    return spinner.itemSelections()
            .skipInitialValue()
            .map { (spinner.adapter as SpinnerAdapter).getItem(it) }

}



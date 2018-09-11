package bushuk.stanislau.bitbucketproject.presentation.snippets

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.api.ScalarApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SnippetsCodeViewModel : ViewModel() {

    @Inject
    lateinit var scalarApi: ScalarApi

    @Inject
    lateinit var api: Api

    val code: MutableLiveData<String> = MutableLiveData()

    init {
        App.component.inject(this)
    }

    fun getCode(url: String) {
        api.getSnippet(url).subscribeOn(Schedulers.io())
                .flatMap { it ->
                    Timber.e(it.files.toString())
                    var single: Single<String>? = null
                    it.files.entries.forEach {
                        single = scalarApi.getCodeSnippet(it.value.links.self.href)
                        return@forEach
                    }
                    return@flatMap single
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    code.postValue(it)
                }, {
                    Timber.e(it.message)
                })
    }
}
package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SnippetsDataSource : PageKeyedDataSource<String, Snippet>() {


    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    val noSnippets: MutableLiveData<Int> = MutableLiveData()

    val noSnippetsText : MutableLiveData<String> = MutableLiveData()

    val loading: MutableLiveData<Int> = MutableLiveData()


    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun invalidate() {
        disposable.clear()
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Snippet>) {
        noSnippets.postValue(View.INVISIBLE)
        loading.postValue(View.VISIBLE)

        disposable.add(userModel.user.observeOn(Schedulers.io())
                .switchMapSingle { api.getSnippets(it.username) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            loading.postValue(View.INVISIBLE)

                            if (it.values.isEmpty()) {
                                noSnippets.postValue(View.VISIBLE)
                                noSnippetsText.postValue(App.resourcesApp.getString(R.string.snippets_screen_no_snippets))
                            }

                            callback.onResult(it.values, it.previous, it.next)
                        },
                        {
                            Timber.e(it.message)
                        }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Snippet>) {
        disposable.clear()

        disposable.add(api.getSnippetsNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Snippet>) {

    }
}
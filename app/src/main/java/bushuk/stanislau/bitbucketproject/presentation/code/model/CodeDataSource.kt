package bushuk.stanislau.bitbucketproject.presentation.code.model

import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CodeDataSource : PageKeyedDataSource<String, Code>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var loadingModel: LoadingModel

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    var path: String? = null

    override fun invalidate() {
        disposable.clear()
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Code>) {

        loadingModel.loading.postValue(View.VISIBLE)
        loadingModel.noInfo.postValue(View.INVISIBLE)

        val request: Single<CodeResponse> = if (path.isNullOrEmpty()) {
            api.getRepoWithName(userModel.user.value.username, repositoryModel.repository.value.uuid)
        } else {
            api.getRepoWithNamePath(userModel.user.value.username, repositoryModel.repository.value.name, path!!)
        }

        disposable.add(request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.size == 0) {
                        loadingModel.noInfo.postValue(View.VISIBLE)
                        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.code_screen_error_text))
                    }

                    callback.onResult(it.values, it.previous, it.next)
                    loadingModel.loading.postValue(View.GONE)

                }, {
                    Timber.e(it.message)
                })

        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Code>) {
        disposable.add(api.getRepoWithNameNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {
                    Timber.e(it.message)
                })

        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Code>) {
    }
}
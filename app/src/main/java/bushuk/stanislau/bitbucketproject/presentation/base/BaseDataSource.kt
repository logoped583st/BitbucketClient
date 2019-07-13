package bushuk.stanislau.bitbucketproject.presentation.base

import android.view.View
import androidx.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class BaseDataSource<Value, Response> : PageKeyedDataSource<String, Value>(),
        IBaseDataSource<Response, Value> {

    private var loadingModel: LoadingModel = LoadingModel()

    private val compositeDisposable = CompositeDisposable()

    private val loadingEvent: PublishSubject<LoadingModel> = PublishSubject.create()

    private fun loading(a: Response) {
        loadingModel.loading = View.INVISIBLE
        loadingModel.noInfo = View.INVISIBLE
        loadingEvent.onNext(loadingModel)
        when (a) {
            is CommentResponse -> showErrorWindow(a.size)

            is RepositoriesResponse -> showErrorWindow(a.size)

            is Followers -> showErrorWindow(a.size)

            is SnippetsResponce -> showErrorWindow(a.values.size)

            is PullRequestResponse -> showErrorWindow(a.size)

            else -> {
                loadingModel.loading = View.INVISIBLE
                loadingModel.noInfo = View.INVISIBLE
                loadingEvent.onNext(loadingModel)
            }
        }
    }

    private fun showErrorWindow(size: Int) {
        if (size == 0) {
            loadingModel.noInfo = View.VISIBLE
        }else{
            loadingModel.noInfo = View.INVISIBLE
        }
        loadingEvent.onNext(loadingModel)

    }


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Value>) {
        loadingEvent.onNext(loadingModel)
        compositeDisposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    onResultInitial(it, callback)
                }, {
                    loadingEvent.onNext(LoadingModel(noInfo = View. VISIBLE,loading = View.INVISIBLE,errorText = "Error or empty"))
                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        compositeDisposable.add(loadNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResult(it, callback)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }
}
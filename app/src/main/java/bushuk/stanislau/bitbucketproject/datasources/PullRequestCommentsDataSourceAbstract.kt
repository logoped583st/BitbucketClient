package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class PullRequestCommentsDataSourceAbstract : BaseDataSource<String, Comment>() {

    abstract val api: Api

    abstract val single: Single<CommentResponse>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Comment>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it.values)
                    callback.onResult(it.values, it.previous, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Comment>) {
        compositeDisposable.add(api.getPullRequestComments(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun invalidate() {
        compositeDisposable.clear()
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Comment>) {

    }
}
package bushuk.stanislau.bitbucketproject.presentation.pullrequest.model

import android.arch.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CommitsDataSource : PageKeyedDataSource<String, Commit>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var pullrequestModel: PullRequestModel

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Commit>) {
        compositeDisposable.add(api.getCommitWithUrl(pullrequestModel.publishSubject.value.links.commits.href).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Commit>) {
        compositeDisposable.add(api.getCommitWithUrl(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Commit>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
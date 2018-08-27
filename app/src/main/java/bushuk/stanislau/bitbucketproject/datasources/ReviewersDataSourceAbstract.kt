package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class ReviewersDataSourceAbstract : BaseDataSource<String, User>() {

    init {
        App.component.inject(this)
    }

    abstract val single: Observable<PullRequest>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.reviewers, null, null)
                }, {

                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        // nothing
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun invalidate() {
        compositeDisposable.clear()
    }
}
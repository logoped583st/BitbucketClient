package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class ReviewersDataSourceAbstract : BaseDataSource<String, User>() {

    abstract val pullRequestModel: PullRequestModel

    abstract val single: Single<PullRequest>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pullRequestModel.publishSubject.onNext(it)
                    callback.onResult(it.reviewers, null, null)
                }, {
                    Timber.e(it.message)
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
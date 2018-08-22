package bushuk.stanislau.bitbucketproject.presentation.pullrequest.model

import android.arch.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewersDataSource : PageKeyedDataSource<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var pullrequestModel: PullRequestModel

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        compositeDisposable.add(api.getPullRequest(
                userModel.user.value.uuid,
                repositoryModel.repository.value.uuid,
                pullrequestModel.publishSubject.value.id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.reviewers, null, null)
                }, {

                }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        compositeDisposable.add(api.getReviewersPullRequest(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.reviewers, null)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
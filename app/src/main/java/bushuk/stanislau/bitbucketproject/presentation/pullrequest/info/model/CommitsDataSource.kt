package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import bushuk.stanislau.bitbucketproject.room.commits.CommitResponse
import io.reactivex.Single
import javax.inject.Inject

class CommitsDataSource : BaseDataSource<Commit, CommitResponse>() {

    override fun onResult(value: CommitResponse, callback: LoadCallback<String, Commit>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: CommitResponse, callback: LoadInitialCallback<String, Commit>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    @Inject
    lateinit var api: Api

    init {
        //App.component.inject(this)
    }


    override val single: Single<CommitResponse>
        get() = api.getCommitWithUrl(pullRequestModel.publishSubject.value!!.links.commits.href)

    override fun loadNextPage(url: String): Single<CommitResponse> = api.getCommitWithUrl(url)

    override val errorText: String = App.resourcesApp.getString(R.string.commits_error_text)

}
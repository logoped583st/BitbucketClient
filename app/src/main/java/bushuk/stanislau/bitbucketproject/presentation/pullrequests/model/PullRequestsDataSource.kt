package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import io.reactivex.Single
import javax.inject.Inject

class PullRequestsDataSource : BaseDataSource<PullRequest, PullRequestResponse>() {
    override fun onResult(value: PullRequestResponse, callback: LoadCallback<String, PullRequest>) {
        callback.onResult(value.values, value.previous)
    }

    override fun onResultInitial(value: PullRequestResponse, callback: LoadInitialCallback<String, PullRequest>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    var queryPullRequest: String = ""

    var sortPullRequest: String = ""

    init {
        //App.component.inject(this)
    }

    override val single: Single<PullRequestResponse> = userModel
            .user.flatMapSingle {
        api.getPullRequests(it.username, repositoryModel.repository.value!!.uuid, queryPullRequest, sortPullRequest)
    }.firstOrError()

    override fun loadNextPage(url: String): Single<PullRequestResponse> = api.getPullRequestNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.pullrequests_screen_error_text)

}
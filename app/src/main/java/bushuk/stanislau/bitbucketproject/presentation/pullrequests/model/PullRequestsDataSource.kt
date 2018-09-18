package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.datasources.PullRequestsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PullRequestsDataSource : PullRequestsDataSourceAbstract() {

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    var queryPullRequst: String = ""

    var sortPullRequest: String = ""

    init {
        App.component.inject(this)
    }

    override val single: Observable<PullRequestResponse> = userModel
            .user.flatMapSingle {
        api.getPullRequests(it.username, repositoryModel.repository.value.uuid, queryPullRequst, sortPullRequest)
    }

    override fun loadNextPage(url: String): Single<PullRequestResponse> = api.getPullRequestNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.pullrequests_screen_error_text)

}
package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.PullRequestsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import io.reactivex.Observable
import io.reactivex.Single

class PullRequestsDataSource : PullRequestsDataSourceAbstract() {

    override val single: Observable<PullRequestResponse> = userModel
            .user.flatMapSingle { api.getPullRequests(it.username, repositoryModel.repository.value.uuid, null) }

    override fun loadNextPage(url: String): Single<PullRequestResponse> = api.getPullRequestNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.pullrequests_screen_error_text)

}
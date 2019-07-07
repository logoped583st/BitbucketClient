package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import javax.inject.Inject

class PullRequestsDataSourceFactory : DataSource.Factory<String, PullRequest>() {

    val pullRequestsDataSource: PullRequestsDataSource = PullRequestsDataSource()

    init {
        //App.component.initPullRequestsComponent().inject(this)
    }

    override fun create(): DataSource<String, PullRequest> {
        return pullRequestsDataSource
    }
}
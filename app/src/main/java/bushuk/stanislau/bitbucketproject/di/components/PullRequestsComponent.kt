package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.pullrequests.PullRequestModule
import bushuk.stanislau.bitbucketproject.di.scopes.PullRequestsScope
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.PullRequestCommentsViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.PullRequestsViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import dagger.Subcomponent

@PullRequestsScope
@Subcomponent(modules = [PullRequestModule::class])
interface PullRequestsComponent {

    fun inject(pullRequestsDataSourceFactory: PullRequestsDataSourceFactory)

    fun inject(pullRequestCommentsViewModel: PullRequestCommentsViewModel)

    fun inject(pullRequestsViewModel: PullRequestsViewModel)

    fun inject(pullRequestCommentsDataSourceFactory: PullRequestCommentsDataSourceFactory)
}
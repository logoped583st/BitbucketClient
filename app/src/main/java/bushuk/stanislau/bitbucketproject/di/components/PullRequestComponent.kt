package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.pullrequest.PullRequestScopeModule
import bushuk.stanislau.bitbucketproject.di.scopes.PullRequestScope
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.PullRequestCommentsViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.PullRequestViewModel
import dagger.Subcomponent

@PullRequestScope
@Subcomponent(modules = [PullRequestScopeModule::class])
interface PullRequestComponent {
    fun inject(pullRequestViewModel: PullRequestViewModel)

    fun inject(pullRequestCommentsViewModel: PullRequestCommentsViewModel)
}
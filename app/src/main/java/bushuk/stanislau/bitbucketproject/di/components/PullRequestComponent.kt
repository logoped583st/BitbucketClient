package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.pullrequest.PullRequestScopeModule
import bushuk.stanislau.bitbucketproject.di.scopes.PullRequestScope
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.PullRequestViewModel
import dagger.Subcomponent

@PullRequestScope
@Subcomponent(modules = [PullRequestScopeModule::class])
interface PullRequestComponent {
    fun inject(pullRequestViewModel: PullRequestViewModel)
}
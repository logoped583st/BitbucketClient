package bushuk.stanislau.bitbucketproject.presentation.pullrequest

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import javax.inject.Inject

class ContainerPullRequestViewModel : ViewModel() {

    @Inject
    lateinit var pullRequest:PullRequestModel

    init {
        App.component.inject(this)
    }
}
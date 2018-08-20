package bushuk.stanislau.bitbucketproject.presentation.pullrequests

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import javax.inject.Inject

class PullRequestsViewModel : ViewModel() {

    @Inject
    lateinit var pullRequestsDataSourceFactory: PullRequestsDataSourceFactory

    val pullRequests: LiveData<PagedList<PullRequest>> by lazy { LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build() }

    init {
        App.component.initPullRequestsComponent().inject(this)
    }
}
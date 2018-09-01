package bushuk.stanislau.bitbucketproject.presentation.pullrequests

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class PullRequestsViewModel : ViewModel() {

    @Inject
    lateinit var pullRequestsDataSourceFactory: PullRequestsDataSourceFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    val pullRequests: LiveData<PagedList<PullRequest>> by lazy { LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build() }

    init {
        App.component.initPullRequestsComponent().inject(this)
    }

    fun navigateToPullRequestScreen(pullRequest: PullRequest) {
        Timber.e(pullRequest.toString())
        pullRequestModel.publishSubject.onNext(pullRequest)
        router.navigateTo(Screens.PULL_REQUEST_SCREEN)
    }

    override fun onCleared() {
        pullRequestsDataSourceFactory.pullRequestsDataSource.invalidate()
        super.onCleared()
    }
}
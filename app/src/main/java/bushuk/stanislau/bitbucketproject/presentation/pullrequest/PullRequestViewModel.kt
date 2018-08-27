package bushuk.stanislau.bitbucketproject.presentation.pullrequest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.ReviewersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PullRequestViewModel : ViewModel() {

    @Inject
    lateinit var pullRequest: PullRequestModel

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var commitDataSourceFactory: CommitsDataSourceFactory

    @Inject
    lateinit var reviewersDataSourceFactory: ReviewersDataSourceFactory

    init {
        App.component.inject(this)
    }

    fun exitFromFragment() {
        router.exit()
    }

    override fun onCleared() {
        commitDataSourceFactory.commitsDataSource.invalidate()
        reviewersDataSourceFactory.reviewersDataSource.invalidate()
        super.onCleared()
    }

    val commits: LiveData<PagedList<Commit>> = LivePagedListBuilder<String, Commit>(commitDataSourceFactory, Constants.listPagedConfig).build()

    val reviewers: LiveData<PagedList<User>> = LivePagedListBuilder<String, User>(reviewersDataSourceFactory, Constants.listPagedConfig).build()
}
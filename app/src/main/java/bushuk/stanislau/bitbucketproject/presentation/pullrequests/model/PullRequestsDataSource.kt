package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.Single
import javax.inject.Inject

class PullRequestsDataSource() : BaseDataSource<PullRequest, BaseListResponse<PullRequest>>() {


    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    var queryPullRequest: String = ""

    var sortPullRequest: String = ""

    init {
        //App.component.inject(this)
    }

    override val single: Single<BaseListResponse<PullRequest>> = TODO()
    //userModel
//            .user.flatMapSingle {
//        api.getPullRequests(it.username, repositoryModel.repository.value!!.uuid!!, queryPullRequest, sortPullRequest)
//    }.firstOrError()

    override fun loadNextPage(url: String): Single<BaseListResponse<PullRequest>> = TODO()//api.getPullRequestNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.pullrequests_screen_error_text)

}
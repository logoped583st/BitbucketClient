package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider

class ReviewersDataSource : BaseDataSource<User, BaseListResponse<User>>() {
    override fun loadNextPage(url: String): Single<BaseListResponse<User>> {
        return Single.error(NullPointerException())//there doesn`t exist next page
    }


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    init {
        //App.component.inject(this)
    }

    override val single: Single<BaseListResponse<User>>
        get() = TODO()
    //api.getPullRequest(userModel.user.value!!.username,
    //repositoryModel.repository.value!!.uuid!!, pullRequestModel.publishSubject.value!!.id.toString())

    override val errorText: String = App.resourcesApp.getString(R.string.reviewers_error_text)

}
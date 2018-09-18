package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.datasources.ReviewersDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.Single
import javax.inject.Inject

class ReviewersDataSource : ReviewersDataSourceAbstract() {


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var userModel: UserModel

    @Inject
    override
    lateinit var pullRequestModel: PullRequestModel

    init {
        App.component.inject(this)
    }

    override val single: Single<PullRequest>
        get() = api.getPullRequest(userModel.user.value.username,
                repositoryModel.repository.value.uuid, pullRequestModel.publishSubject.value.id.toString())

    override val errorText: String = App.resourcesApp.getString(R.string.reviewers_error_text)

}
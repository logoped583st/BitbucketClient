package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.ReviewersDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.Single

class ReviewersDataSource : ReviewersDataSourceAbstract() {

    override val single: Single<PullRequest>
        get() = api.getPullRequest(userModel.user.value.username,
                repositoryModel.repository.value.uuid, pullrequestModel.publishSubject.value.id.toString())

    override val errorText: String = App.resourcesApp.getString(R.string.reviewers_error_text)

}
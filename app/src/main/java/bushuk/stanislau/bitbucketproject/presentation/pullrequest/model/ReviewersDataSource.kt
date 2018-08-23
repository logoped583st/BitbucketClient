package bushuk.stanislau.bitbucketproject.presentation.pullrequest.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.ReviewersDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.Observable

class ReviewersDataSource : ReviewersDataSourceAbstract() {

    override val single: Observable<PullRequest> = pullrequestModel.publishSubject.switchMapSingle {
        api.getPullRequest(userModel.user.value.username, repositoryModel.repository.value.uuid, it.id.toString())
    }

    override val errorText: String = App.resourcesApp.getString(R.string.reviewers_error_text)

}
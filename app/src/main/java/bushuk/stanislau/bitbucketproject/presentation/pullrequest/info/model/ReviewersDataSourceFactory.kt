package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class ReviewersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var reviewersDataSource: ReviewersDataSource

    init {
        //App.component.inject(this)
    }


    override fun create(): DataSource<String, User> = reviewersDataSource

}
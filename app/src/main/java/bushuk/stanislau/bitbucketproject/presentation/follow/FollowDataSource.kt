package bushuk.stanislau.bitbucketproject.presentation.follow

import bushuk.stanislau.bitbucketproject.datasources.UsersDataSourceAbstract

abstract class FollowDataSource : UsersDataSourceAbstract() {

    abstract fun doOnEmpty()
}
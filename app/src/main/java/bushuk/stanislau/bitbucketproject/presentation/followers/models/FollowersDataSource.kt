package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.room.user.User

class FollowersDataSource : PageKeyedDataSource<String, User>() {

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
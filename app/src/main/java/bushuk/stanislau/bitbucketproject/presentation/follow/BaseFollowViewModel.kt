package bushuk.stanislau.bitbucketproject.presentation.follow

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.LoadingModel
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

abstract class BaseFollowViewModel : ViewModel() {

    @Inject
    lateinit var loadingModel: LoadingModel

    init {
        App.component.inject(this)
    }

    abstract var factory: DataSource.Factory<String, User>

    val followers: LiveData<PagedList<User>> by lazy {  LivePagedListBuilder<String, User>(factory, Constants.listPagedConfig).build() }
}
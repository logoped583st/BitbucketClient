package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesDataSourceFactory: RepositoriesDataSourceFactory

    private var repositories: LiveData<PagedList<Repository>>

    fun getRepositories(): LiveData<PagedList<Repository>> = repositories

    init {
        App.component.inject(this)

        val listPagedConfig = PagedList.Config.Builder()
                .setPageSize(Constants.ITEMS_IN_PAGE)
                .setInitialLoadSizeHint(Constants.ITEMS_IN_PAGE)
                .setEnablePlaceholders(true)
                .build()

        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, listPagedConfig).build()
    }
}
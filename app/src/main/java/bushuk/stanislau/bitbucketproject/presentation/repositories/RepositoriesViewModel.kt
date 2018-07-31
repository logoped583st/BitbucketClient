package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.widget.SearchView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesDataSourceFactory: RepositoriesDataSourceFactory

    init {
        App.component.inject(this)
    }

    var searchText: String? = null


    var repositories: LiveData<PagedList<Repository>> = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()


    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxSearchView.queryTextChanges(searchView)
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    searchText = it
                    repositoriesFound(lifecycleOwner, it, adapter)
                }
    }

    private fun repositoriesFound(lifecycleOwner: LifecycleOwner, search: String?, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        repositoriesDataSourceFactory.repositoriesDataSource.query = UrlBuilder.queryFindBuilder(1, search)
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCleared() {
        repositoriesDataSourceFactory.repositoriesDataSource.invalidate()
        super.onCleared()
    }
}
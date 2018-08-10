package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.widget.SearchView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    @Inject
    lateinit var repositoriesDataSourceFactory: RepositoriesDataSourceFactory

    var language: MutableLiveData<String> = MutableLiveData()


    init {
        App.component.inject(this)
    }

    var repositories: LiveData<PagedList<Repository>> = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()


    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxSearchView.queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    repositoriesNameChange(lifecycleOwner, it, adapter)
                }
    }


    private fun repositoriesNameChange(lifecycleOwner: LifecycleOwner, search: String?, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        UrlBuilder.queryRepositoryNameBuilder(search)
        repositoriesDataSourceFactory.repositoriesDataSource.query = UrlBuilder.buildQuery()
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    fun repositoriesLanguageChange(language: String, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        UrlBuilder.queryLanguageBuilder(language)
        repositoriesDataSourceFactory.repositoriesDataSource.query = UrlBuilder.buildQuery()
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    fun repositoriesAccessChange(access:String,lifecycleOwner: LifecycleOwner,adapter: RecyclerRepositoriesAdapter){
        repositories.removeObservers(lifecycleOwner)
        UrlBuilder.queryAccessBuilder(access)
        repositoriesDataSourceFactory.repositoriesDataSource.query = UrlBuilder.buildQuery()
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCleared() {
        repositoriesDataSourceFactory.repositoriesDataSource.invalidate()
        super.onCleared()
    }

}
package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.SearchView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel(private val factory: RepositoriesDataSourceFactory = RepositoriesDataSourceFactory(),
                            private val source: BaseDataSource<Repository, RepositoriesResponse> = factory.repositoriesDataSource )
    : LoadingViewModel<Repository, RepositoriesResponse, RepositoriesDataSourceFactory>(factory, source) {

//    override var dataSourceFactory: RepositoriesDataSourceFactory = RepositoriesDataSourceFactory()

//    override var dataSource: BaseDataSource<Repository, RepositoriesResponse> = dataSourceFactory.repositoriesDataSource

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryModel: RepositoryModel

    private var repositoryName: String? = null

    private var repositoryAccess: String? = null

    private var repositoryLanguage: String? = null

    init {
        App.component.inject(this)
    }

    var repositories: LiveData<PagedList<Repository>> = LivePagedListBuilder<String, Repository>(dataSourceFactory, Constants.listPagedConfig).build()


    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxSearchView.queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    repositoryName = it
                    queryChange(lifecycleOwner, adapter)
                }
    }


    fun observeLanguageChangeSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    repositoryLanguage = it
                    queryChange(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })
    }

    fun observeAccessChangeSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    repositoryAccess = it
                    queryChange(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })
    }


    private fun queryChange(lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        dataSourceFactory.repositoriesDataSource.url = UrlBuilder.buildQuery(repositoryName, repositoryAccess, repositoryLanguage)
        repositories = LivePagedListBuilder<String, Repository>(dataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    fun exitFromFragment() {
        router.exit()
    }

    fun navigateToRepositoryScreen(repository: Repository, username: String) {
        repositoryModel.repository.onNext(repository)
        router.navigateTo(Screens.REPOSITORY_SCREEN, listOf(repository.links.avatar.href, username))
    }

    override fun onCleared() {
        super.onCleared()
        dataSourceFactory.repositoriesDataSource.invalidate()
    }

}
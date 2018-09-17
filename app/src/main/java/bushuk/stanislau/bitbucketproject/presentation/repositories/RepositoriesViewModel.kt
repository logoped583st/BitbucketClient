package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.SearchView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {

    private var access: String? = null

    private var languageString: String? = null

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var loadingModel: LoadingModel

    init {
        App.component.inject(this)
    }

    var repositoriesDataSourceFactory: RepositoriesDataSourceFactory = RepositoriesDataSourceFactory(access, languageString, loadingModel)

    var repositories: LiveData<PagedList<Repository>> = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()

    val language: MutableLiveData<String> = MutableLiveData()


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
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }


    fun observeLanguageChangeSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    repositoriesLanguageChange(it!!, lifecycleOwner, adapter)
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
                    repositoriesAccessChange(it!!, lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })
    }


    private fun repositoriesLanguageChange(language: String, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        repositoriesDataSourceFactory = RepositoriesDataSourceFactory(access, language,loadingModel)
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    private fun repositoriesAccessChange(access: String, lifecycleOwner: LifecycleOwner, adapter: RecyclerRepositoriesAdapter) {
        repositories.removeObservers(lifecycleOwner)
        repositoriesDataSourceFactory = RepositoriesDataSourceFactory(access, languageString,loadingModel)
        repositories = LivePagedListBuilder<String, Repository>(repositoriesDataSourceFactory, Constants.listPagedConfig).build()
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
        repositoriesDataSourceFactory.repositoriesDataSource.invalidate()
    }

}
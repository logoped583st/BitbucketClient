package bushuk.stanislau.bitbucketproject.presentation.pullrequests

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.SearchView
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerPullRequestsAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PullRequestsViewModel : ViewModel() {

    @Inject
    lateinit var pullRequestsDataSourceFactory: PullRequestsDataSourceFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var pullRequestModel: PullRequestModel


    init {
        App.component.initPullRequestsComponent().inject(this)
    }

    var pullRequests: LiveData<PagedList<PullRequest>> = LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build()


    fun navigateToPullRequestScreen(pullRequest: PullRequest) {
        pullRequestModel.publishSubject.onNext(pullRequest)
        router.navigateTo(Screens.PULL_REQUEST_SCREEN)
    }

    override fun onCleared() {
        pullRequestsDataSourceFactory.pullRequestsDataSource.invalidate()
        super.onCleared()
    }

    fun observeStateSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        val disposable = RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    pullRequestStatusChange(it!!, lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })

        pullRequestsDataSourceFactory.pullRequestsDataSource.compositeDisposable.add(disposable)
    }

    fun observeSortSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        val disposable = RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    pullRequestSortChange(it!!, lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })

        pullRequestsDataSourceFactory.pullRequestsDataSource.compositeDisposable.add(disposable)
    }

    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        val disposable = RxSearchView.queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    pullRequestTitleChange(it, lifecycleOwner, adapter)
                }
        pullRequestsDataSourceFactory.pullRequestsDataSource.compositeDisposable.add(disposable)
    }

    private fun pullRequestStatusChange(status: String, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        pullRequests.removeObservers(lifecycleOwner)
        UrlBuilder.pullRequestStateBuilder(status)
        pullRequests = LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build()
        pullRequests.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    private fun pullRequestTitleChange(title: String, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        pullRequests.removeObservers(lifecycleOwner)
        UrlBuilder.pullRequestNameBuilder(title)
        pullRequests = LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build()
        pullRequests.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    private fun pullRequestSortChange(sort:String,lifecycleOwner: LifecycleOwner,adapter: RecyclerPullRequestsAdapter){
        pullRequests.removeObservers(lifecycleOwner)
        UrlBuilder.buildSortPullRequest(sort)
        pullRequests = LivePagedListBuilder<String, PullRequest>(pullRequestsDataSourceFactory, Constants.listPagedConfig).build()
        pullRequests.observe(lifecycleOwner, Observer(adapter::submitList))
    }
}
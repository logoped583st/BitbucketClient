package bushuk.stanislau.bitbucketproject.presentation.pullrequests

import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.adapters.RecyclerPullRequestsAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PullRequestsViewModel(val factory: PullRequestsDataSourceFactory = PullRequestsDataSourceFactory(),
                            source: BaseDataSource<PullRequest, PullRequestResponse> = factory.pullRequestsDataSource)
    : LoadingViewModel<PullRequest, PullRequestResponse>(source) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    var statePullRequest: String = "Open"

    var namePullRequest: String? = null

    var sortPullRequest: String = "Id up"


    init {
        App.component.initPullRequestsComponent().inject(this)
        factory.pullRequestsDataSource.queryPullRequest = UrlBuilder.buildQueryPullRequest(namePullRequest, statePullRequest)
        factory.pullRequestsDataSource.sortPullRequest = UrlBuilder.buildSortPullRequest(sortPullRequest)
    }


    var pullRequests: LiveData<PagedList<PullRequest>> = LivePagedListBuilder<String, PullRequest>(factory, Constants.listPagedConfig).build()


    fun navigateToPullRequestScreen(pullRequest: PullRequest) {
        pullRequestModel.publishSubject.onNext(pullRequest)
        //router.navigateTo(Screens.PULL_REQUEST_SCREEN)
    }

    override fun onCleared() {
        factory.pullRequestsDataSource.invalidate()
        super.onCleared()
    }

    fun observeStateSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    statePullRequest = it!!
                    change(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                })

    }

    fun observeSortSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        compositeDisposable.add(RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    sortPullRequest = it!!
                    change(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                }))
    }

    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        compositeDisposable.add(RxSearchView
                .queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    namePullRequest = it
                    change(lifecycleOwner, adapter)
                })
    }


    private fun change(lifecycleOwner: LifecycleOwner, adapter: RecyclerPullRequestsAdapter) {
        pullRequests.removeObservers(lifecycleOwner)
        factory.pullRequestsDataSource.queryPullRequest = UrlBuilder.buildQueryPullRequest(namePullRequest, statePullRequest)
        factory.pullRequestsDataSource.sortPullRequest = UrlBuilder.buildSortPullRequest(sortPullRequest)
        pullRequests = LivePagedListBuilder<String, PullRequest>(factory, Constants.listPagedConfig).build()
        pullRequests.observe(lifecycleOwner, Observer(adapter::submitList))
    }

}
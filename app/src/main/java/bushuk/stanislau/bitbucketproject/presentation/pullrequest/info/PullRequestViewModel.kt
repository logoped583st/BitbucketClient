package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.ReviewersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestParticipants
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PullRequestViewModel(private val reviewersDataSourceFactory: ReviewersDataSourceFactory = ReviewersDataSourceFactory()) : ListLoadingViewModel<PullRequest>(TODO()) {
    override val state: LiveData<LoadingStateSealed<PullRequest, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    @Inject
    lateinit var pullRequest: PullRequestModel

    //@Inject
  //  lateinit var router: Router

    @Inject
    lateinit var commitDataSourceFactory: CommitsDataSourceFactory

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    val approveAction: MutableLiveData<Boolean> = MutableLiveData()

    val pullRequestState: MutableLiveData<String> = MutableLiveData()

    val countOfApproves: MutableLiveData<List<PullRequestParticipants>> = MutableLiveData()

    val isApproved: MutableLiveData<Boolean> = MutableLiveData()

    val commits: LiveData<PagedList<Commit>> by lazy { LivePagedListBuilder<String, Commit>(commitDataSourceFactory, Constants.listPagedConfig).build() }

    val reviewers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(reviewersDataSourceFactory, Constants.listPagedConfig).build() }

    init {
        isApproved.postValue(false)
        //App.component.inject(this)
        pullRequest.publishSubject
                .subscribe { it ->
                    pullRequestState.postValue(it.state)
                    if (it.participants != null) {
                        countOfApproves.postValue(it.participants)
                        it.participants.forEach {
                            if (it.user.uuid == userModel.user.value!!.uuid &&
                                    it.approved) {
                                isApproved.postValue(true)
                                return@forEach
                            }
                        }
                    }
                }

        zipLiveData(commits, reviewers)
    }

    override fun onCleared() {
        commitDataSourceFactory.commitsDataSource.invalidate()
        reviewersDataSourceFactory.reviewersDataSource.invalidate()
        super.onCleared()
    }

    fun navigateToUser(data: User) {
        //router.navigateTo(Screens.USER_SCREEN, data)
    }

    fun approveButtonClick(view: View) {
        (view as Button).isClickable = false
        val completable: Completable = if (isApproved.value!!) {
            api.unApprovePullRequest(pullRequest.publishSubject.value!!.links.approve.href)
        } else {
            api.approvePullRequest(pullRequest.publishSubject.value!!.links.approve.href)
        }

        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.isClickable = true
                    if (view.text == "Approve") {
                        isApproved.postValue(true)
                        approveAction.postValue(true)
                    } else {
                        isApproved.postValue(false)
                        approveAction.postValue(false)
                    }
                }, {
                    view.isClickable = true
                })

    }

    fun mergePullRequest(view: View) {
        (view as Button).isClickable = false
        api.mergePullRequest(pullRequest.publishSubject.value!!.links.merge.href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pullRequest.publishSubject.onNext(it)
                    pullRequestState.postValue(it.state)
                    view.text = "Revert"
                    Snackbar.make(view, "Merged successful", Snackbar.LENGTH_LONG).show()
                }, {
                    Timber.e(it.message)
                    view.isClickable = true
                    Snackbar.make(view, it.localizedMessage, Snackbar.LENGTH_LONG).show()
                })
    }


    fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
        return MediatorLiveData<Pair<A, B>>().apply {
            var lastA: A? = null
            var lastB: B? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                if (localLastA != null && localLastB != null) {
                    this.value = Pair(localLastA, localLastB)
                    Timber.e("PAIR")
                    // loadingModel.loading.postValue(View.VISIBLE)
                }
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
        }
    }

}
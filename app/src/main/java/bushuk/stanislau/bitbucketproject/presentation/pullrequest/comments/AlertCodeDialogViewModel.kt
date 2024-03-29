package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.api.ScalarApi
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AlertCodeDialogViewModel : ViewModel() {

    @Inject
    lateinit var scalarApi: ScalarApi

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var pullRequestModel: PullRequestModel


    val code: MutableLiveData<String> = MutableLiveData()

    init {
        //App.component.inject(this)
    }

    fun setComment(comment: Comment) {
        //loadingModel.loading.postValue(View.VISIBLE)
        UrlBuilder.buildPathWithHash(comment.inline!!.path, pullRequestModel.publishSubject.value!!.source.commit.hash)
        scalarApi.getCodeOfFile(userModel.user.value!!.username, repositoryModel.repository.value!!.uuid!!, UrlBuilder.repositoryPath!!)
                .subscribeOn(Schedulers.io())
                .map {
                    val lines = it.split(System.getProperty("line.separator"))
                    val finalStringBuilder = StringBuilder("")
                    if (comment.inline.from == null) {
                        if (comment.inline.to == null) {
                            for (i in 0..lines.lastIndex) {
                                finalStringBuilder.append(lines[i]).append(System.getProperty("line.separator"))
                            }
                        } else {
                            for (i in 0..comment.inline.to) {
                                finalStringBuilder.append(lines[i]).append(System.getProperty("line.separator"))
                            }
                        }
                    } else {
                        if (comment.inline.to == null) {
                            for (i in comment.inline.from..lines.lastIndex) {
                                finalStringBuilder.append(lines[i]).append(System.getProperty("line.separator"))
                            }
                        } else {
                            for (i in comment.inline.from..comment.inline.to) {
                                finalStringBuilder.append(lines[i]).append(System.getProperty("line.separator"))
                            }
                        }
                    }

                    return@map finalStringBuilder.toString()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    code.postValue(it)
                }, {
                    Timber.e(it.message)
                })
    }
}
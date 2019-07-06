package bushuk.stanislau.bitbucketproject.presentation.code

import android.widget.Spinner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodeAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.code.Branch
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class CodeViewModel(val factory: CodeDataSourceFactory = CodeDataSourceFactory(), val source: BaseDataSource<Code, CodeResponse> = factory.codeDataSource) :
        LoadingViewModel<Code, CodeResponse>(source) {
    override val state: LiveData<LoadingState.LoadingStateSealed<CodeResponse, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    @Inject
    lateinit var router: Router

    val branches: MutableLiveData<List<Branch>> = MutableLiveData()

    private lateinit var hash: String

    init {
        App.component.initCodeComponent().inject(this)

        compositeDisposable.add(factory.codeDataSource.api.getBranchWithUrl(factory
                .codeDataSource.repositoryModel.repository.value!!.links.branches.href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var i = 0
                    while (i < it.values.size) {
                        if (factory.codeDataSource.repositoryModel.repository.value!!.mainbranch!!.name == it.values[i].name) {
                            hash = it.values[i].target.hash
                        }
                        i++
                    }
                    branches.postValue(it.values.reversed())

                }, {
                    Timber.e(it.message)
                })
        )

    }

    fun observeSpinner(spinner: Spinner, adapter: RecyclerCodeAdapter, lifecycleOwner: LifecycleOwner) {
        compositeDisposable.add(RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {

                    val branchName: String = (spinner.adapter as SpinnerAdapter).getItem(it)
                    var i = 0
                    while (i < branches.value?.size!!) {
                        if (branches.value?.get(i)!!.name == branchName) {
                            hash = branches.value?.get(i)?.target?.hash!!
                        }
                        i++
                    }

                }
                .subscribe({
                    Timber.e("RESULT")
                    reloadPathWithHash(lifecycleOwner, adapter, "src")
                }, {
                    Timber.e(it.message)
                }))
    }

    var code: LiveData<PagedList<Code>> = LivePagedListBuilder<String, Code>(factory, Constants.listPagedConfig).build()


    fun navigateToCodeEditor(code: Code) {
        val position = code.path.lastIndexOf('/')
        val fileName = when (position) {
            -1 -> code.path
            else -> code.path.subSequence(position + 1, code.path.length).toString()
        }
        UrlBuilder.buildPathWithHash(code.path, hash)
        //router.navigateTo(Screens.CODE_EDITOR_SCREEN, fileName)
    }

    fun reloadPathWithHash(lifecycleOwner: LifecycleOwner, adapter: RecyclerCodeAdapter, path: String) {
        code.removeObservers(lifecycleOwner)
        UrlBuilder.buildPathWithHash(path, hash)
        code = LivePagedListBuilder<String, Code>(factory, Constants.listPagedConfig).build()
        code.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCleared() {
        UrlBuilder.repositoryPath = null //хардкорно сбиваем путь
        factory.codeDataSource.invalidate()
        super.onCleared()
    }
}
package bushuk.stanislau.bitbucketproject.presentation.code

import android.widget.Spinner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDisposableViewModel
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.code.Branch
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CodeViewModel(val factory: CodeDataSourceFactory = CodeDataSourceFactory(), val source: BaseDataSource<Code, CodeResponse> = factory.codeDataSource) :
        BaseDisposableViewModel() {

    //@Inject
  //  lateinit var router: Router

    val branches: MutableLiveData<List<Branch>> = MutableLiveData()

    private lateinit var hash: String

    init {
        //App.component.initCodeComponent().inject(this)

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

    fun observeSpinner(spinner: Spinner, adapter: RecyclerAdapter<Code>, lifecycleOwner: LifecycleOwner) {
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

    fun reloadPathWithHash(lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Code>, path: String) {
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
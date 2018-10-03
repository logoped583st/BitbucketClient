package bushuk.stanislau.bitbucketproject.presentation.code

import android.arch.lifecycle.*
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.widget.Spinner
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodeAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.code.Branch
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class CodeViewModel : ViewModel() {

    @Inject
    lateinit var codeDataSourceFactory: CodeDataSourceFactory

    @Inject
    lateinit var router: Router

    val branches: MutableLiveData<List<Branch>> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()


    private lateinit var hash: String

    init {
        App.component.initCodeComponent().inject(this)

        disposable.add(codeDataSourceFactory.codeDataSource.api.getBranchWithUrl(codeDataSourceFactory
                .codeDataSource.repositoryModel.repository.value.links.branches.href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var i = 0
                    while (i < it.values.size) {
                        if (codeDataSourceFactory.codeDataSource.repositoryModel.repository.value.mainbranch.name == it.values[i].name) {
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
        RxAdapterView.itemSelections(spinner)
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
                })
    }

    var code: LiveData<PagedList<Code>> = LivePagedListBuilder<String, Code>(codeDataSourceFactory, Constants.listPagedConfig).build()


    fun navigateToCodeEditor(code: Code) {
        val position = code.path.lastIndexOf('/')
        val fileName = when (position) {
            -1 -> code.path
            else -> code.path.subSequence(position + 1, code.path.length).toString()
        }
        UrlBuilder.buildPathWithHash(code.path, hash)
        router.navigateTo(Screens.CODE_EDITOR_SCREEN, fileName)
    }

    fun reloadPathWithHash(lifecycleOwner: LifecycleOwner, adapter: RecyclerCodeAdapter, path: String) {
        code.removeObservers(lifecycleOwner)
        UrlBuilder.buildPathWithHash(path, hash)
        code = LivePagedListBuilder<String, Code>(codeDataSourceFactory, Constants.listPagedConfig).build()
        code.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCleared() {
        UrlBuilder.repositoryPath = null //хардкорно сбиваем путь
        codeDataSourceFactory.codeDataSource.invalidate()
        disposable.clear()
        super.onCleared()
    }
}
package bushuk.stanislau.bitbucketproject.presentation.code

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.widget.Spinner
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodeAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.code.Branch
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CodeViewModel : ViewModel() {

    @Inject
    lateinit var codeDataSourceFactory: CodeDataSourceFactory


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


    fun reloadPathWithHash(lifecycleOwner: LifecycleOwner, adapter: RecyclerCodeAdapter, path: String) {
        code.removeObservers(lifecycleOwner)
        UrlBuilder.buildPathWithHash(path, hash)
        codeDataSourceFactory.codeDataSource.path = UrlBuilder.repositoryPath
        code = LivePagedListBuilder<String, Code>(codeDataSourceFactory, Constants.listPagedConfig).build()
        code.observe(lifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCleared() {
        codeDataSourceFactory.codeDataSource.invalidate()
        disposable.clear()
        super.onCleared()
    }
}
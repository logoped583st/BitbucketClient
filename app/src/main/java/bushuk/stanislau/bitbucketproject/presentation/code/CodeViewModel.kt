package bushuk.stanislau.bitbucketproject.presentation.code

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodeAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CodeViewModel : ViewModel() {

    @Inject
    lateinit var codeDataSourceFactory: CodeDataSourceFactory


    val branches: MutableLiveData<List<String>> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()


    lateinit var hash: String

    init {
        App.component.initCodeComponent().inject(this)


        disposable.add(codeDataSourceFactory.codeDataSource.api.getBranchWithUrl(codeDataSourceFactory
                .codeDataSource.repositoryModel.repository.value.links.branches.href)
                .subscribeOn(Schedulers.io())
                .map<List<String>> { it ->

                    hash = it.values[0].target.hash
                    val list: MutableList<String> = ArrayList()
                    it.values.forEach {
                        list.add(it.name)
                    }
                    return@map list.toList().asReversed()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    branches.postValue(it)
                }, {
                    Timber.e(it.message)
                })
        )

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
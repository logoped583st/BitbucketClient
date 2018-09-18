package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class UsersDataSourceAbstract : BaseDataSource<String, User>() {


    abstract val single: Observable<Followers>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    abstract fun loadNextPage(url: String): Single<Followers>

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        compositeDisposable.add(loadNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidate() {
        compositeDisposable.clear()
    }
}
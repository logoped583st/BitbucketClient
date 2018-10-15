package bushuk.stanislau.bitbucketproject.presentation.addrepository

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.repositories.CreateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class AddRepositoryViewModel : ViewModel() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var api: Api

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    fun exitFromFragment() {
        router.exit()
    }


    fun createRepository(slug: String, description: String, isPrivate: Boolean) {
        val trimedSlug = slug.toLowerCase().replace(" ", "")
        compositeDisposable.add(api.createRepository(userModel.user.value.uuid, trimedSlug, CreateRepository(slug, description, isPrivate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    router.exitWithMessage("Repository Create successfully")
                }, {
                    Timber.e(it.message)
                }))
    }
}
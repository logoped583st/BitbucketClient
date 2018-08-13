package bushuk.stanislau.bitbucketproject.presentation.repository

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryViewModel : ViewModel(){

    @Inject
    lateinit var router: Router

    init {
        App.component.inject(this)
    }

    fun exitFromFragment(){
        router.exit()
    }

}
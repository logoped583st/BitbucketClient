package bushuk.stanislau.bitbucketproject.presentation.main.model

import bushuk.stanislau.bitbucketproject.App
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainScreenModel {


    @Inject
    lateinit var router: Router


    fun init() {
        App.component.inject(this)
    }

}


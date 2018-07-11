package bushuk.stanislau.bitbucketproject

import android.app.Application
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application() {


    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder().build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}
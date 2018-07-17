package bushuk.stanislau.bitbucketproject

import android.app.Application
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import bushuk.stanislau.bitbucketproject.di.modules.ApplicationContextProvider
import bushuk.stanislau.bitbucketproject.di.modules.TokenPreferencesModule
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder()
                .tokenPreferencesModule(TokenPreferencesModule(this))
                .applicationContextProvider(ApplicationContextProvider(this))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}
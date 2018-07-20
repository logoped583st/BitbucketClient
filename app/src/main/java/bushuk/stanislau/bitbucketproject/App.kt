package bushuk.stanislau.bitbucketproject

import android.app.Application
import android.content.Context
import android.content.res.Resources
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var resourcesApp: Resources
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        resourcesApp = resources
        component = DaggerMainComponent.builder()
                .roomModule(RoomModule(this))
                .cryptoModule(CryptoModule(this))
                .preferencesModule(PreferencesModule(this))
                .applicationContextProvider(ApplicationContextProvider(this))
                .retrofitModule(RetrofitModule())
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}
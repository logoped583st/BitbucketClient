package bushuk.stanislau.bitbucketproject

import android.app.Application
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import bushuk.stanislau.bitbucketproject.di.modules.ApplicationContextProvider
import bushuk.stanislau.bitbucketproject.di.modules.CryptoModule
import bushuk.stanislau.bitbucketproject.di.modules.PreferencesModule
import bushuk.stanislau.bitbucketproject.di.modules.RetrofitModule
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder()
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
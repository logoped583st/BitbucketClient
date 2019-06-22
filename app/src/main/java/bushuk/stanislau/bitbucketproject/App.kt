package bushuk.stanislau.bitbucketproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.ViewConfiguration
import androidx.multidex.MultiDex
import bushuk.stanislau.bitbucketproject.di.components.AndroidInjectorComponent
import bushuk.stanislau.bitbucketproject.di.components.DaggerAndroidInjectorComponent
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import java.lang.reflect.Field
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var resourcesApp: Resources
        lateinit var component: MainComponent
        lateinit var androidInjectorComponent: AndroidInjectorComponent
    }

    override fun onCreate() {
        super.onCreate()
        resourcesApp = resources


        androidInjectorComponent = DaggerAndroidInjectorComponent.builder().create(this) as AndroidInjectorComponent
        androidInjectorComponent.inject(this)

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

        try {
            val config = ViewConfiguration.get(this)
            val menuKeyField: Field? = ViewConfiguration::class.java.getDeclaredField("sHasPermanentMenuKey")
            if (menuKeyField != null) {
                menuKeyField.isAccessible = true
                menuKeyField.setBoolean(config, false)
            }
        } catch (ex: Exception) {
            // Ignore
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
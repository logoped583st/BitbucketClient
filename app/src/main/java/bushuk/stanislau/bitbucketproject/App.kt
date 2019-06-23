package bushuk.stanislau.bitbucketproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.ViewConfiguration
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.multidex.MultiDex
import bushuk.stanislau.bitbucketproject.di.components.DaggerAndroidInjectorComponent
import bushuk.stanislau.bitbucketproject.di.components.DaggerMainComponent
import bushuk.stanislau.bitbucketproject.di.components.MainComponent
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import java.lang.reflect.Field
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var resourcesApp: Resources
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        resourcesApp = resources


        DaggerAndroidInjectorComponent.builder()
                .preferenceModule(PreferencesModule(this))
                .cryptoModule(CryptoModule(this))
                .create(this)
                .inject(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })


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

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(
                                        fm: FragmentManager,
                                        f: Fragment,
                                        savedInstanceState: Bundle?
                                ) {
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }
                                }
                            }, true
                    )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}

interface Injectable

package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.AuthLoginActivity
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import ru.terrakok.cicerone.android.SupportAppNavigator

class MainNavigator(activity: FragmentActivity?, containerId: Int) : SupportAppNavigator(activity, containerId) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        when (screenKey) {
            Screens.LOGIN_SCREEN -> return Intent(context, LoginActivity::class.java)

            Screens.MAIN_SCREEN -> return Intent(context, MainScreenActivity::class.java)

            Screens.LOGIN_AUTH_SCREEN -> return Intent(context,AuthLoginActivity::class.java)
        }

        return null
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {

        when(screenKey){
            Screens.REPOSITORIES_SCREEN -> return RepositoriesFragment()
        }
        return null
    }

}
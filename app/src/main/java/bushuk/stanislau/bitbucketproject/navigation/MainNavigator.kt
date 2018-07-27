package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginActivity
import bushuk.stanislau.bitbucketproject.presentation.followers.FollowersFragment
import bushuk.stanislau.bitbucketproject.presentation.following.FollowingFragment
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment
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

            Screens.FOLLOWERS_SCREEN ->return FollowersFragment()

            Screens.FOLLOWING_SCREEN ->return FollowingFragment()

            Screens.SNIPPETS_SCREEN -> return SnippetsFragment()
        }
        return null
    }

}
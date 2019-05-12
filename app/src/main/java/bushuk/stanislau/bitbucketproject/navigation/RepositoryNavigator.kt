package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.transition.Slide
import android.view.Gravity
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.code.CodeFragment
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.PullRequestsFragment
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersFragment
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command


class RepositoryNavigator(activity: FragmentActivity?, fragmentManager: FragmentManager?, containerId: Int) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nextFragment?.enterTransition = Slide(Gravity.LEFT)
        }
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {

        when (screenKey) {
            Screens.CODE_SCREEN -> {
                UrlBuilder.resetCodePath()
                return CodeFragment()
            }

            Screens.PULL_REQUESTS_SCREEN -> return PullRequestsFragment()

            Screens.WATCHERS_SCREEN -> return WatchersFragment()
        }

        return null
    }
}
package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.code.CodeFragment
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.PullRequestsFragment
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersFragment
import ru.terrakok.cicerone.android.SupportAppNavigator


class RepositoryNavigator(activity: FragmentActivity?, fragmentManager: FragmentManager?, containerId: Int) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {

        when (screenKey) {
            Screens.CODE_SCREEN -> return CodeFragment()

            Screens.PULL_REQUESTS_SCREEN -> return PullRequestsFragment()

            Screens.WATCHERS_SCREEN -> return WatchersFragment()
        }

        return null
    }
}
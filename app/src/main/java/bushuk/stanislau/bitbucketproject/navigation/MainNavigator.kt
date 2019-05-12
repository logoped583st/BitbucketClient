package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.transition.Slide
import android.view.Gravity
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.addrepository.AddRepositoryFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginActivity
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorActivity
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingFragment
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.ContainerPullRequestFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryFragment
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsCodeActivity
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment
import bushuk.stanislau.bitbucketproject.presentation.user.UserFragment
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainNavigator(activity: FragmentActivity?, containerId: Int) : SupportAppNavigator(activity, containerId) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        when (screenKey) {
            Screens.LOGIN_SCREEN -> return Intent(context, LoginActivity::class.java)

            Screens.MAIN_SCREEN -> return Intent(context, MainScreenActivity::class.java)

            Screens.LOGIN_AUTH_SCREEN -> return Intent(context, AuthLoginActivity::class.java)

            Screens.CODE_EDITOR_SCREEN -> {
                val intent = Intent(context, CodeEditorActivity::class.java)
                intent.putExtra("FILENAME", data as String)
                return intent
            }

            Screens.SNIPPETS_CODE_SCREEN -> {
                val intent = Intent(context, SnippetsCodeActivity::class.java)
                intent.putExtra("Path", data as String)
                return intent
            }

        }

        return null
    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {

        if(nextFragment is RepositoryFragment || currentFragment is RepositoriesFragment)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nextFragment?.enterTransition = Slide(Gravity.LEFT)
            nextFragment?.exitTransition = Slide(Gravity.RIGHT)
        }
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {

        when (screenKey) {
            Screens.REPOSITORIES_SCREEN -> return RepositoriesFragment()

            Screens.FOLLOWERS_SCREEN -> return FollowersFragment()

            Screens.FOLLOWING_SCREEN -> return FollowingFragment()

            Screens.SNIPPETS_SCREEN -> return SnippetsFragment()

            Screens.USER_SCREEN -> {
                val fragment = UserFragment()
                val bundle = Bundle()

                bundle.putString("USERNAME", (data as User).username)
                bundle.putString("DISPLAYNAME", data.display_name)
                bundle.putString("AVATAR", data.links.avatar.href)
                fragment.arguments = bundle
                return fragment
            }

            Screens.REPOSITORY_SCREEN -> {
                val bundle = Bundle()
                val fragment = RepositoryFragment()
                bundle.putString("AVATAR", (data as List<*>)[0].toString())
                bundle.putString("USERNAME", data[1].toString())
                fragment.arguments = bundle
                return fragment
            }

            Screens.PULL_REQUEST_SCREEN -> return ContainerPullRequestFragment()

            Screens.ADD_REPOSITORY_SCREEN -> return AddRepositoryFragment()
        }
        return null
    }

}
package bushuk.stanislau.bitbucketproject.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.presentation.addrepository.AddRepositoryFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.LoginWebFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersFragment
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenFragment
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.PullRequestFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryFragment
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment
import bushuk.stanislau.bitbucketproject.presentation.user.UserFragment
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object ScreensNavigator {

    class LoginScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = AuthLoginFragment()
    }

    class WebLoginScreen:SupportAppScreen(){
        override fun getFragment(): Fragment = LoginWebFragment()
    }


    class StartScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    class MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainScreenFragment()
        }
    }

    class RepositoriesScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RepositoriesFragment()
        }
    }

    class FollowersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FollowersFragment()
        }
    }

    class SnippetsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SnippetsFragment()
        }
    }

    class UserScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UserFragment()
        }
    }

    class RepositoryScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RepositoryFragment()
        }
    }

    class WatchersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return WatchersFragment()
        }
    }

    class PullRequestScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return PullRequestFragment()
        }
    }

    class CodeEditorScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            TODO()
        }
    }

    class SnippetsCodeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            TODO()
        }
    }


    class AddRepositoryScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AddRepositoryFragment()
        }
    }

}
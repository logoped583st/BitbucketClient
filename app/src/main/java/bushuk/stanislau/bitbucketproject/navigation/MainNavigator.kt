package bushuk.stanislau.bitbucketproject.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainNavigator(activity: FragmentActivity?, fragmentManager: FragmentManager?, containerId: Int)
    : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun setupFragmentTransaction(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
    }
}
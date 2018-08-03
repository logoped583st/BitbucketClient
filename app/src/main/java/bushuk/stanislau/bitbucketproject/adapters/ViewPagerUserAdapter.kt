package bushuk.stanislau.bitbucketproject.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment
import timber.log.Timber


class ViewPagerUserAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val repositories = 0
    private val followers = 1
    private val following = 2
    private val snippets = 3

    override fun getItem(position: Int): Fragment? {
        Timber.e(position.toString())

        return when (position) {

            repositories -> RepositoriesFragment()

            followers -> FollowersFragment()

            following -> FollowingFragment()

            snippets -> SnippetsFragment()

            else -> null
        }
    }


    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {

            0 -> "Repositories"

            1 -> "Followers"

            2 -> "Following"

            3 -> "Snippets"

            else -> null
        }
    }


}
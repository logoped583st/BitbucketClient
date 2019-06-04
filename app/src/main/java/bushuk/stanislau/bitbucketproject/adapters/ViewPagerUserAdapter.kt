package bushuk.stanislau.bitbucketproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment


class ViewPagerUserAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val repositories = 0
    private val followers = 1
    private val following = 2
    private val snippets = 3

    override fun getItem(position: Int): Fragment? {
        return when (position) {

            repositories -> RepositoriesFragment()

            followers -> FollowersFragment()


            following -> FollowingFragment()


            snippets -> SnippetsFragment()


            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
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
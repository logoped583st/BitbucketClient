package bushuk.stanislau.bitbucketproject.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import bushuk.stanislau.bitbucketproject.presentation.code.CodeFragment
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.PullRequestCommentsFragment
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.PullRequestFragment
import timber.log.Timber

class ViewPagerRepositoryAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val code = 0
    private val comments = 1

    override fun getItem(position: Int): Fragment? {
        Timber.e(position.toString())

        return when (position) {

            code -> PullRequestFragment()

            comments -> PullRequestCommentsFragment()


            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {

            0 -> "Info"

            1 -> "Comments"

            else -> null
        }
    }
}
package bushuk.stanislau.bitbucketproject.utils.binding

import android.databinding.BindingAdapter
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import bushuk.stanislau.bitbucketproject.adapters.ViewPagerUserAdapter
import bushuk.stanislau.bitbucketproject.presentation.user.UserFragment


class ViewPagerBinding {

    companion object {
        @JvmStatic
        @BindingAdapter("handler")
        fun bindViewPagerAdapter(viewPager: ViewPager, fragment: UserFragment) {
            val pagerAdapter = ViewPagerUserAdapter(fragment.fragmentManager!!)
            viewPager.adapter = pagerAdapter
        }

        @JvmStatic
        @BindingAdapter("pager")
        fun bindViewPagerTabs(tabView: TabLayout, pagerView: ViewPager) {
            tabView.setupWithViewPager(pagerView, true)
        }
    }
}
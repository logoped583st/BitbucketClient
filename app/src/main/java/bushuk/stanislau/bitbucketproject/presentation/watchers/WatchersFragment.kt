package bushuk.stanislau.bitbucketproject.presentation.watchers


import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment


class WatchersFragment : BaseFollowFragment<WatchersViewModel>() {

    override var viewModelClass: Class<WatchersViewModel> = WatchersViewModel::class.java
}

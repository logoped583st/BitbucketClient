package bushuk.stanislau.bitbucketproject

import android.arch.paging.PagedList

object Constants {
    const val TOKEN_PREFERENCES: String = "TOKEN_PREFERENCES"

    const val AUTH_URL: String = "https://bitbucket.org/site/oauth2/authorize?client_id=yuPL4qKCg7VvRzAWAm&response_type=token"

    const val KEY_STORE: String = "AndroidKeyStore"

    const val GET_TOKEN_BROWSER: Int = 1

    const val ITEMS_IN_PAGE: Int = 10

    val listPagedConfig = PagedList.Config.Builder()
            .setPageSize(Constants.ITEMS_IN_PAGE)
            .setInitialLoadSizeHint(Constants.ITEMS_IN_PAGE)
            .setEnablePlaceholders(true)
            .build()!!
}
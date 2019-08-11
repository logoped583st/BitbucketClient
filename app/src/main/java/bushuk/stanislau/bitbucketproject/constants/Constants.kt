package bushuk.stanislau.bitbucketproject.constants

import androidx.paging.PagedList

object Constants {
    const val ACCESS_TOKEN_PREFERENCES: String = "ACCESS_TOKEN_PREFERENCES"

    const val REFRESH_TOKEN_PREFERENCES: String = "REFRESH_TOKEN_PREFERENCES"

    const val TOKEN_PREFERENCES: String = "TOKEN_PREFERENCES"

    const val AUTH_URL: String = "https://bitbucket.org/site/oauth2/authorize?client_id=yuPL4qKCg7VvRzAWAm&response_type=code"

    const val CLIENT_ID = "yuPL4qKCg7VvRzAWAm"

    const val SECRET_ID = "mx7r7DdG3GTM5Ju7MeaBxsqUFquKJdtp"

    const val KEY_STORE: String = "AndroidKeyStore"

    const val GET_TOKEN_BROWSER: Int = 1

    const val GRANT_TYPE_AUTH_CODE = "authorization_code"

    const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"

    private const val ITEMS_IN_PAGE: Int = 10

    val listPagedConfig = PagedList.Config.Builder()
            .setPageSize(ITEMS_IN_PAGE)
            .setInitialLoadSizeHint(0)
            .setPrefetchDistance(ITEMS_IN_PAGE)
            .setEnablePlaceholders(false)
            .build()

    enum class TokenTypes(val type: String) {
        BEARER("Bearer"),
        BASIC("Basic")
    }

    const val TOKEN_HEADER = "Authorization"
}
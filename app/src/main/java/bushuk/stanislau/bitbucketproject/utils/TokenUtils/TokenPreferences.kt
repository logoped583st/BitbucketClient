package bushuk.stanislau.bitbucketproject.utils.TokenUtils


interface TokenPreferences {
    fun getToken(): String?
    fun setToken(accessToken: String)
}
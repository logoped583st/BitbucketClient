package bushuk.stanislau.bitbucketproject.room

import com.google.gson.annotations.SerializedName

data class OauthResponse(
        @SerializedName("access_token")
        val accessToken: String,
        val scopes: String,
        @SerializedName("expires_in")
        val expireTime: Long,
        @SerializedName("refresh_token")
        val refreshToken: String,
        @SerializedName("token_type")
        val tokenType: String
)
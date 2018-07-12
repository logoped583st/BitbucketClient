package bushuk.stanislau.bitbucketproject.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(

        @SerializedName("username")
        @Expose
        val username: String? = null,
        @SerializedName("website")
        @Expose
        val website: String? = null,
        @SerializedName("display_name")
        @Expose
        val displayName: String? = null,
        @SerializedName("account_id")
        @Expose
        val accountId: String? = null,
        @SerializedName("links")
        @Expose
        val links: Any? = null,
        @SerializedName("created_on")
        @Expose
        val createdOn: String? = null,
        @SerializedName("is_staff")
        @Expose
        val isStaff: Boolean? = null,
        @SerializedName("location")
        @Expose
        val location: Any? = null,
        @SerializedName("type")
        @Expose
        val type: String? = null,
        @SerializedName("uuid")
        @Expose
        val uuid: String? = null

)
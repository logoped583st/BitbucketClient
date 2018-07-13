package bushuk.stanislau.bitbucketproject.pojo


data class User(

        val username: String,

        val website: String? = null,

        val display_name: String,

        val accountId: String,

        val links: Links,

        val createdOn: String,

        val isStaff: Boolean,

        val location: Any,

        val type: String,

        val uuid: String

)
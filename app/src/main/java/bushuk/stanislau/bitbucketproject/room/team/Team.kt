package bushuk.stanislau.bitbucketproject.room.team

import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("username")
        val userName: String,
        @SerializedName("display_name")
        val links: TeamLinks,
        val displayName: String,
        @SerializedName("created_on")
        val createdOn: String,
        val type: String
) : ItemResponse()

class TeamResponse : BaseListResponse<Team>()

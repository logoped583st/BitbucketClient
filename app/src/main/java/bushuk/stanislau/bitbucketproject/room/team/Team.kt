package bushuk.stanislau.bitbucketproject.room.team

import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import com.google.gson.annotations.SerializedName
import java.util.*

data class Team(
        @SerializedName("username")
        val userName: String,
        @SerializedName("display_name")
        val displayName: String,
        val links: TeamLinks,
        @SerializedName("created_on")
        val createdOn: Date,
        val type: String
) : ItemResponse()

class TeamResponse : BaseListResponse<Team>()

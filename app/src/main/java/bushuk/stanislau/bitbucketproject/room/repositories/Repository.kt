package bushuk.stanislau.bitbucketproject.room.repositories

import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import java.util.*

data class Repository(val name: String,
                      val is_private: Boolean,
                      val description: String,
                      val updated_on: Date,
                      val links: Links,
                      val mainbranch: MainBranch?,
                      val language: String,
                      val full_name: String,
                      val owner: Owner) : ItemResponse()

class RepositoriesResponse : BaseListResponse<Repository>()
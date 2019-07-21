package bushuk.stanislau.bitbucketproject.room.code

import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.room.commits.Commit

data class Code(val path: String,
                val type: String,
                val commit: Commit,
                val links: CodeLinks): ItemResponse()
package bushuk.stanislau.bitbucketproject.room.code

data class Code(val path: String,
                val type: String,
                val commit:Commit,
                val links: CodeLinks)
package bushuk.stanislau.bitbucketproject.room.commits

import bushuk.stanislau.bitbucketproject.room.Href
import bushuk.stanislau.bitbucketproject.room.user.User
import java.util.*

data class Commit(val hash: String,
                  val comments: Href,
                  val author: CommitAuthor,
                  val date: Date,
                  val message: String)
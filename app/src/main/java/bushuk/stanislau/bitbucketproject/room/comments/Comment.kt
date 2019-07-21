package bushuk.stanislau.bitbucketproject.room.comments

import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import java.util.*

data class Comment(val user: User,
                   val id: Int,
                   val updated_on: Date,
                   val links: CommentLinks,
                   val inline: CommentInline?,
                   val content: CommentContent):ItemResponse()
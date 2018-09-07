package bushuk.stanislau.bitbucketproject.room.comments

data class CommentResponse(val pagelen: Int,
                           val next: String,
                           val previous: String,
                           val size: Int,
                           val values: MutableList<Comment>)
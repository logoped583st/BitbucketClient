package bushuk.stanislau.bitbucketproject.room.pullrequest

data class PullRequestResponse(val pagelen: Int,
                               val previous:String,
                               val size:Int,
                               val next:String,
                               val values:MutableList<PullRequest>
)
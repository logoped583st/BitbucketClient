package bushuk.stanislau.bitbucketproject.room.commits

data class CommitResponse(val pagelen: Int,
                          val next: String,
                          val previous: String,
                          val values: MutableList<Commit>,
                          val size: Int)
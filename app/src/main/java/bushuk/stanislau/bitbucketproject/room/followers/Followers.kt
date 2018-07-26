package bushuk.stanislau.bitbucketproject.room.followers

import bushuk.stanislau.bitbucketproject.room.user.User

data class Followers(val values: MutableList<User>,
                     val pagelen: Int,
                     val size : Int,
                     val next : String,
                     val previous: String)

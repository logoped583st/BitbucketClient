package bushuk.stanislau.bitbucketproject.room.followers

import bushuk.stanislau.bitbucketproject.room.user.User

data class Followers (val user: User,val pagelen:Int,val previous:Int)

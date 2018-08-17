package bushuk.stanislau.bitbucketproject.room.code

data class BranchesResponse(val pagelen:Int,
                            val next:String,
                            val previous:String,
                            val values:MutableList<Branch>)
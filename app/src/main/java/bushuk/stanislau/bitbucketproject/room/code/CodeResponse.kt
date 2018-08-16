package bushuk.stanislau.bitbucketproject.room.code

data class CodeResponse(val pagelen: Int,
                        val page: Int,
                        val size: Int,
                        val next: String,
                        val values: MutableList<Code>,
                        val previous: String)
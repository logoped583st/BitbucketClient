package bushuk.stanislau.bitbucketproject.room.repositories

data class RepositoriesResponse(
        val pagelen: String,
        val size: Int,
        val values: MutableList<Repository>,
        val next: String,
        val previous: String)
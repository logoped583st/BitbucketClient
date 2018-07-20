package bushuk.stanislau.bitbucketproject.room.repositories

data class RepositoriesResponse(
        val pagelen: String,
        val size: String,
        val values: MutableList<Repository>
        /*val valuses:ArrayList<>*/)
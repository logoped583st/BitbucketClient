package bushuk.stanislau.bitbucketproject.room.repositories

data class CreateRepository(val slug: String,
                            val description: String = "",
                            val is_private: Boolean = false)
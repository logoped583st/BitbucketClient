package bushuk.stanislau.bitbucketproject.room.repositories

data class Repository(val uuid: String,
                      val name: String,
                      val is_private: Boolean,
                      val description: String,
                      val updated_on: String,
                      val language: String)
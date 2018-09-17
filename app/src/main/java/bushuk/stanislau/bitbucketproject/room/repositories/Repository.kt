package bushuk.stanislau.bitbucketproject.room.repositories

import java.util.*

data class Repository(val uuid: String,
                      val name: String,
                      val is_private: Boolean,
                      val description: String,
                      val updated_on: Date,
                      val links: Links,
                      val mainbranch: MainBranch?,
                      val language: String,
                      val owner: Owner)
package bushuk.stanislau.bitbucketproject.room.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import bushuk.stanislau.bitbucketproject.pojo.Links

@Entity
data class MeUser(val username: String,

                  val website: String? = null,

                  val display_name: String,

                  val accountId: String,

//                  val links: Links,

                  val createdOn: String,

                  val isStaff: Boolean,

//                  val location: Any,

                  val type: String,

                  @PrimaryKey
                  val uuid: String)
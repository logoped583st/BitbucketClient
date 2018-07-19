package bushuk.stanislau.bitbucketproject.room.user

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "User")
data class User(val username: String,

                val website: String? = null,

                val display_name: String,

                val account_id: String?,

                @Embedded
                val links: Links,

                val created_on: Date,

                val is_Staff: Boolean,

//                  val location: Any,

                val type: String,

                @PrimaryKey
                val uuid: String)
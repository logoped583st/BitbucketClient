package bushuk.stanislau.bitbucketproject.room.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import java.util.*

@Entity(tableName = "User")
data class User(var username: String,

                val website: String? = null,

                val display_name: String,
                @PrimaryKey
                val account_id: String,

                @Embedded
                val links: Links,

                val created_on: Date,

                val is_Staff: Boolean,

//                  val location: Any,

                val type: String):ItemResponse()
package bushuk.stanislau.bitbucketproject.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bushuk.stanislau.bitbucketproject.room.Href

@Entity
data class Links(
        @ColumnInfo(name = FieldsUserTable.HOOKS)
        val hooks: Href,

        @ColumnInfo(name = FieldsUserTable.SELF)
        val self: Href,

        @ColumnInfo(name = FieldsUserTable.HTML)
        val html: Href,

        @ColumnInfo(name = FieldsUserTable.FOLLOWERS)
        val followers: Href,

        @PrimaryKey
        @ColumnInfo(name = FieldsUserTable.AVATAR)
        val avatar: Href)
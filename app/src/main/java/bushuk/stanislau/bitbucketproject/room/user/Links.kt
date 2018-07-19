package bushuk.stanislau.bitbucketproject.room.user

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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
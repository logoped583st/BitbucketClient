package bushuk.stanislau.bitbucketproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bushuk.stanislau.bitbucketproject.room.converters.DateConverter
import bushuk.stanislau.bitbucketproject.room.user.Links
import bushuk.stanislau.bitbucketproject.room.dao.MeUserDAO
import bushuk.stanislau.bitbucketproject.room.converters.LinksConverter
import bushuk.stanislau.bitbucketproject.room.user.User

@Database(entities = [User::class, Links::class ], version = 1)
@TypeConverters(LinksConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): MeUserDAO

}
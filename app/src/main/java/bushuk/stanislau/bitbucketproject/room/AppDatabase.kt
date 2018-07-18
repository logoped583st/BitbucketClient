package bushuk.stanislau.bitbucketproject.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import bushuk.stanislau.bitbucketproject.room.dao.MeUserDAO
import bushuk.stanislau.bitbucketproject.room.user.MeUser

@Database(entities = [(MeUser::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): MeUserDAO

}
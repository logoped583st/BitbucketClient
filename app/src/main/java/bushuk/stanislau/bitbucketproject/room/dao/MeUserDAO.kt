package bushuk.stanislau.bitbucketproject.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import bushuk.stanislau.bitbucketproject.room.user.MeUser

@Dao
interface MeUserDAO {

    @Insert
    fun insertUser(user : MeUser)

    @Query("SELECT * FROM MeUser")
    fun getMeUser():MeUser
}
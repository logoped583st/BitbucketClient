package bushuk.stanislau.bitbucketproject.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MeUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM User LIMIT 1")
    fun getMeUser(): Single<User>
}
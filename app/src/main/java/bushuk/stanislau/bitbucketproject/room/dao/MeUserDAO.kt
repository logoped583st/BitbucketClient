package bushuk.stanislau.bitbucketproject.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single

@Dao
interface MeUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM User LIMIT 1")
    fun getMeUser(): Single<User>
}
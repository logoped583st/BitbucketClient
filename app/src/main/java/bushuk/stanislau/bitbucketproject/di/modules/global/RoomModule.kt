package bushuk.stanislau.bitbucketproject.di.modules.global

import androidx.room.Room
import android.content.Context
import bushuk.stanislau.bitbucketproject.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(val context: Context) {

    @Provides
    @Singleton
    fun provideRoom() = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
}
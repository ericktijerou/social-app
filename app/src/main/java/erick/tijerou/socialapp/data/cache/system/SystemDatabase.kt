package erick.tijerou.socialapp.data.cache.system

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import erick.tijerou.socialapp.data.cache.dao.UserDao
import erick.tijerou.socialapp.data.cache.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class SystemDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun newInstance(context: Context): SystemDatabase {
            return Room.databaseBuilder(context, SystemDatabase::class.java, "social-system.db").build()
        }
    }
}
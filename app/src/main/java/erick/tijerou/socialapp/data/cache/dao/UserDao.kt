package erick.tijerou.socialapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import erick.tijerou.socialapp.data.cache.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    suspend fun getAll(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: UserEntity)

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity

    @Query("DELETE FROM User")
    suspend fun deteleAll()
}
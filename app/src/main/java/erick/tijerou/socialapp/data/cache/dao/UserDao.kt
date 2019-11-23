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
    suspend fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM User WHERE id = :userId")
    suspend fun getUserById(userId: Long): UserEntity

    @Query("UPDATE User SET favourite = :isFavourite WHERE id = :userId")
    suspend fun setFavourite(userId: Long, isFavourite: Boolean)

    @Query("DELETE FROM User")
    suspend fun deteleAll()
}
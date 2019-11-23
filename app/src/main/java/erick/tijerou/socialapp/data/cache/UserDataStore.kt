package erick.tijerou.socialapp.data.cache

import erick.tijerou.socialapp.data.cache.dao.UserDao
import erick.tijerou.socialapp.data.cache.entity.toData
import erick.tijerou.socialapp.data.entity.UserModel
import erick.tijerou.socialapp.data.entity.toLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataStore(private val userDao: UserDao) {

    suspend fun getUsers(): List<UserModel> = withContext(Dispatchers.IO) {
        userDao.getAll().map { it.toData() }
    }

    suspend fun getUser(userId: Long): UserModel = withContext(Dispatchers.IO) {
        userDao.getUserById(userId).toData()
    }

    suspend fun saveUsers(users: List<UserModel>): List<UserModel> = withContext(Dispatchers.IO) {
        userDao.deteleAll()
        val userLocalList = users.map { it.toLocal() }
        userDao.insertAll(*userLocalList.toTypedArray())
        userLocalList.map { it.toData() }
    }

    suspend fun setFavourite(userId: Long, isFavourite: Boolean) {
        userDao.setFavourite(userId, isFavourite)
    }
}
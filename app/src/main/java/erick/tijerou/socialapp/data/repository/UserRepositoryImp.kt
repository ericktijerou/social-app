package erick.tijerou.socialapp.data.repository

import erick.tijerou.socialapp.data.cache.UserDataStore
import erick.tijerou.socialapp.data.entity.toDomain
import erick.tijerou.socialapp.data.network.UserCloudStore
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImp(
    private val cloudStore: UserCloudStore,
    private val userDataStore: UserDataStore
) : UserRepository {

    override suspend fun getUsers(refresh: Boolean): List<User> = withContext(Dispatchers.IO) {
        val userLocalList = getUsersFromLocal()
        if (userLocalList.isNotEmpty() &&  !refresh) {
            if (userDataStore.isExpired()) {
                getUsersFromRemote()
            } else {
                userLocalList
            }
        } else {
            getUsersFromRemote()
        }
    }

    private suspend fun getUsersFromRemote(): List<User> = withContext(Dispatchers.IO) {
        val usersFromRemote = cloudStore.getUsers()
        if (usersFromRemote.isNotEmpty()) {
            userDataStore.saveUsers(usersFromRemote).map { it.toDomain() }
        } else {
            getUsersFromLocal()
        }
    }

    private suspend fun getUsersFromLocal(): List<User> = withContext(Dispatchers.IO) {
        userDataStore.getUsers().map { it.toDomain() }
    }

    override suspend fun getUser(userId: Long): User = withContext(Dispatchers.IO) {
        userDataStore.getUser(userId).toDomain()
    }

    override suspend fun setFavourite(userId: Long, isFavourite: Boolean) = withContext(Dispatchers.IO) {
        userDataStore.setFavourite(userId, isFavourite)
    }
}
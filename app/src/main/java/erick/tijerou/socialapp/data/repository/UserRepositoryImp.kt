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
        val userLocalList = userDataStore.getUsers().map { it.toDomain() }
        if (userLocalList.isEmpty() || refresh) {
            val remote = cloudStore.getUsers()
            userDataStore.saveUsers(remote).map { it.toDomain() }
        } else {
            userLocalList
        }

    }

    override suspend fun getUser(userId: Long): User = withContext(Dispatchers.IO) {
        userDataStore.getUser(userId).toDomain()
    }

    override suspend fun setFavourite(userId: Long, isFavourite: Boolean) {
        userDataStore.setFavourite(userId, isFavourite)
    }
}
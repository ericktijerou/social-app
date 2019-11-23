package erick.tijerou.socialapp.domain.repository

import erick.tijerou.socialapp.domain.entity.User

interface UserRepository {
    suspend fun getUsers(refresh: Boolean): List<User>
    suspend fun getUser(userId: Long): User
    suspend fun setFavourite(userId: Long, isFavourite: Boolean)
}
package erick.tijerou.socialapp.domain.repository

import erick.tijerou.socialapp.domain.entity.User

interface UserRepository {
    suspend fun getUsers(refresh: Boolean): List<User>
    suspend fun getUser(id: Long): User
}
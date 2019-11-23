package erick.tijerou.socialapp.domain.interactor

import erick.tijerou.socialapp.domain.entity.User

interface UserInteractor {
    suspend fun getUsers(refresh: Boolean): List<User>
    suspend fun getUser(id: Long): User
}

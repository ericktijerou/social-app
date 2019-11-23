package erick.tijerou.socialapp.domain.interactor

import erick.tijerou.socialapp.domain.entity.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
}

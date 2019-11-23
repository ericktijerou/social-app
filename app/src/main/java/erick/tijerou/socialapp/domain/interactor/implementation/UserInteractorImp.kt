package erick.tijerou.socialapp.domain.interactor.implementation

import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserInteractorImp(
    private val repository: UserRepository
) : UserInteractor {

    override suspend fun getUsers(refresh: Boolean): List<User> = withContext(IO) {
        repository.getUsers(refresh)
    }

    override suspend fun getUser(id: Long): User = withContext(IO) {
        repository.getUser(id)
    }

    override suspend fun setFavourite(userId: Long, isFavourite: Boolean) {
        repository.setFavourite(userId, isFavourite)
    }
}

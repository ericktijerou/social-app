package erick.tijerou.socialapp.data.repository

import erick.tijerou.socialapp.data.network.api.SocialApi
import erick.tijerou.socialapp.data.network.entity.toDomain
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.exception.ServerException
import erick.tijerou.socialapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImp(
    private val api: SocialApi
) : UserRepository {

    override suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        api.getUsers().let { response ->
            response.body()?.results?.map { it.toDomain() } ?: throw ServerException()
        }
    }
}
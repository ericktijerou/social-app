package erick.tijerou.socialapp.data.network

import erick.tijerou.socialapp.data.entity.UserModel
import erick.tijerou.socialapp.data.network.api.SocialApi
import erick.tijerou.socialapp.data.network.entity.UserResponse
import erick.tijerou.socialapp.data.network.entity.toData
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.exception.ServerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserCloudStore(private val api: SocialApi) {

    suspend fun getUsers(): List<UserModel> = withContext(Dispatchers.IO) {
        api.getUsers().let { response ->
            response.body()?.results?.map { it.toData() } ?: throw ServerException()
        }
    }
}
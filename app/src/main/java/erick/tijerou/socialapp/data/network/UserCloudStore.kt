package erick.tijerou.socialapp.data.network

import erick.tijerou.socialapp.data.entity.UserModel
import erick.tijerou.socialapp.data.network.api.SocialApi
import erick.tijerou.socialapp.data.network.entity.toData
import erick.tijerou.socialapp.core.exception.NotFoundException
import erick.tijerou.socialapp.core.util.NetworkConnectivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserCloudStore(private val api: SocialApi,
                     private val networkConnectivity: NetworkConnectivity) {

    suspend fun getUsers(): List<UserModel> = withContext(Dispatchers.IO) {
        if (networkConnectivity.isInternetOn()) {
            api.getUsers().let { response ->
                response.body()?.results?.map { it.toData() } ?: throw NotFoundException()
            }
        } else {
            arrayListOf()
        }
    }
}
package erick.tijerou.socialapp.data.network.api

import erick.tijerou.socialapp.data.network.entity.BaseResponse
import erick.tijerou.socialapp.data.network.entity.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SocialApi {

    @GET("api/?results=20")
    suspend fun getUsers(): Response<BaseResponse<List<UserResponse>>>

}
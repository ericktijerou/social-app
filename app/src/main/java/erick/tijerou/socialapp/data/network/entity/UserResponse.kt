package erick.tijerou.socialapp.data.network.entity

import erick.tijerou.socialapp.domain.entity.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("gender")
    val gender: String,
    @SerialName("name")
    val name: NameResponse,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("cell")
    val cell: String,
    @SerialName("picture")
    val picture: PictureResponse,
    @SerialName("nat")
    val nat: String
)

fun UserResponse.toDomain() = User(
    name = name.toDomain(),
    email = email,
    picture = picture.toDomain()
)
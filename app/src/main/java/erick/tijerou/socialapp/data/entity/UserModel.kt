package erick.tijerou.socialapp.data.entity

import erick.tijerou.socialapp.data.cache.entity.UserEntity
import erick.tijerou.socialapp.domain.entity.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserModel(
    val id: Long = 0,
    val gender: String,
    val name: NameModel,
    val email: String,
    val picture: PictureModel
)

fun UserModel.toDomain() = User(
    id = id,
    gender = gender,
    name = name.toDomain(),
    email = email,
    picture = picture.toDomain()
)

fun UserModel.toLocal() = UserEntity (
    id = 0,
    gender = gender,
    title = name.title,
    first = name.first,
    lastName = name.last,
    email = email,
    imageLarge = picture.large,
    imageMedium = picture.medium,
    thumbnail = picture.thumbnail
)
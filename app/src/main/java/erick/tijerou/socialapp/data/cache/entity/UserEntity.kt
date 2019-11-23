package erick.tijerou.socialapp.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import erick.tijerou.socialapp.data.entity.NameModel
import erick.tijerou.socialapp.data.entity.PictureModel
import erick.tijerou.socialapp.data.entity.UserModel

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val gender: String,
    val title: String,
    val first: String,
    val lastName: String,
    val email: String,
    val imageLarge: String,
    val imageMedium: String,
    val thumbnail: String
)

fun UserEntity.toData() = UserModel(
    id = id,
    gender = gender,
    name = NameModel(title, first, lastName),
    email = email,
    picture = PictureModel(imageLarge, imageMedium, thumbnail)
)
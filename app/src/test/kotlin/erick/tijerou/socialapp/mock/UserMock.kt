package erick.tijerou.socialapp.mock

import erick.tijerou.socialapp.data.entity.NameModel
import erick.tijerou.socialapp.data.entity.PictureModel
import erick.tijerou.socialapp.data.entity.UserModel
import erick.tijerou.socialapp.data.network.entity.UserResponse
import erick.tijerou.socialapp.domain.entity.Name
import erick.tijerou.socialapp.domain.entity.Picture
import erick.tijerou.socialapp.domain.entity.User

object UserMock {

    val oneUser: User get() = User(
        id = 1L,
        gender = "",
        name = Name("", "", ""),
        email = "String",
        picture = Picture("", "", ""),
        favourite = false
    )

    val oneUserModel: UserModel get() = oneUser.toModel()

    private fun User.toModel() = UserModel(
        id = id,
        gender = gender,
        name = NameModel("", "", ""),
        email = email,
        picture = PictureModel("", "", ""),
        favourite = favourite
    )


}
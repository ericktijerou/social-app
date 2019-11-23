package erick.tijerou.socialapp.domain.entity

class User(
    val id: Long = 0,
    val gender: String,
    val name: Name,
    val email: String,
    val picture: Picture,
    val favourite: Boolean
)
package erick.tijerou.socialapp.data.entity

import erick.tijerou.socialapp.domain.entity.Name
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class NameModel(
    val title: String,
    val first: String,
    val last: String
)

fun NameModel.toDomain() = Name(
    title = title,
    first = first,
    last = last
)
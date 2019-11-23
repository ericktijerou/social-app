package erick.tijerou.socialapp.data.network.entity

import erick.tijerou.socialapp.domain.entity.Name
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameResponse(
    @SerialName("title")
    val title: String,
    @SerialName("first")
    val first: String,
    @SerialName("last")
    val last: String
)

fun NameResponse.toDomain() = Name(
    title = title,
    first = first,
    last = last
)
package erick.tijerou.socialapp.data.network.entity

import erick.tijerou.socialapp.domain.entity.Picture
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureResponse(
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("thumbnail")
    val thumbnail: String
)

fun PictureResponse.toDomain() = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)
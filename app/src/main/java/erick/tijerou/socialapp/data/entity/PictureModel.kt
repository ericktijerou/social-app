package erick.tijerou.socialapp.data.entity

import erick.tijerou.socialapp.domain.entity.Picture

data class PictureModel(
    val large: String,
    val medium: String,
    val thumbnail: String
)

fun PictureModel.toDomain() = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)
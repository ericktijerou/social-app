package erick.tijerou.socialapp.data.network.entity

open class BaseResponse<T> {
    var info: InfoResponse? = null
    var results: T? = null
}
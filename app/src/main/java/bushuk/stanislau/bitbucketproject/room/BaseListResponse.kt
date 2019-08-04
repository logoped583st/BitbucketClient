package bushuk.stanislau.bitbucketproject.room

import com.google.gson.annotations.SerializedName

open class  BaseListResponse<out T : ItemResponse> {

    @SerializedName("pagelen")
    var pagelen: Int = 0
    @SerializedName("previous")
    var previous: String? = null
    @SerializedName("size")
    var size: Int = 0
    @SerializedName("next")
    var next: String? = null
    @SerializedName("values")
    val items: List<T>? = null
}

open class ItemResponse {

    var uuid: String? = null

}
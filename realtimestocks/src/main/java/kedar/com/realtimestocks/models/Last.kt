package kedar.com.realtimestocks.models


import com.squareup.moshi.Json

data class Last(
    @Json(name = "cond1")
    val cond1: Int?,
    @Json(name = "exchange")
    val exchange: Int?,
    @Json(name = "price")
    val price: Double,
    @Json(name = "size")
    val size: Int?,
    @Json(name = "timestamp")
    val timestamp: Long?
)
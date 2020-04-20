package kedar.com.realtimestocks.models


import com.squareup.moshi.Json

data class StockInfo(
    @Json(name = "last")
    val last: Last,
    @Json(name = "status")
    val status: String?,
    @Json(name = "symbol")
    val symbol: String
)
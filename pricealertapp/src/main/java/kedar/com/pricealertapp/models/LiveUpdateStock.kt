package kedar.com.pricealertapp.models

import java.math.BigDecimal

data class LiveUpdateStock(val symbol:String, val livePrice: BigDecimal? = null, val alertPrice: BigDecimal, val magnitude: Magnitude){
    override fun equals(other: Any?): Boolean {
        return symbol == (other as LiveUpdateStock).symbol
                && magnitude == other.magnitude
                && alertPrice == other.alertPrice
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + alertPrice.hashCode()
        result = 31 * result + magnitude.hashCode()
        return result
    }
}
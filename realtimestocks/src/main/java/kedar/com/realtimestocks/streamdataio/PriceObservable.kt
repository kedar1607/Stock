package kedar.com.realtimestocks.streamdataio

interface PriceObservable {
    fun newPrice(price: String)
}
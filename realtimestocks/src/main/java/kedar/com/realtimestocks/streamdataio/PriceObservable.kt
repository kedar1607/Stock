package kedar.com.realtimestocks.streamdataio

import kedar.com.realtimestocks.models.StockInfo

interface PriceObservable {
    fun newPrice(stockInfo: StockInfo)
}
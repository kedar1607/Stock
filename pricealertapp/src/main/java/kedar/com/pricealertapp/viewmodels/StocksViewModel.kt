package kedar.com.pricealertapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kedar.AlertApplication
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.LiveUpdateStock
import kedar.com.pricealertapp.services.MyListObserver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StocksViewModel: ViewModel(), MyListObserver {
    val dispatcher: CoroutineDispatcher = Dispatchers.IO

    val _stocks = MutableLiveData<List<LiveUpdateStock>>()
    val stocks : LiveData<List<LiveUpdateStock>> = _stocks

    fun loadAllActiveStocks(){
        AlertApplication.instance.mService.addListObserver(this)
        _stocks.postValue(mapToLiveUpdateStocks(AlertApplication.instance.mService.getActiveStocks()))
    }

    override fun pushUpdate(list: List<AlertSetUp>) {
        _stocks.postValue(mapToLiveUpdateStocks(list))
    }

    private fun mapToLiveUpdateStocks(list: List<AlertSetUp>) : List<LiveUpdateStock> =
        list.map { LiveUpdateStock(it.symbol, it.livePrice, it.tippingPoint, it.magnitude) }

    fun activateAlert(liveUpdateStock: LiveUpdateStock){

    }

}
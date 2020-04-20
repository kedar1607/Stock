package kedar.com.pricealertapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kedar.com.realtimestocks.models.StockInfo
import kedar.com.realtimestocks.streamdataio.RealTimeDataConnection
import kedar.com.pricealertapp.R
import kedar.com.realtimestocks.streamdataio.PriceObservable
import kedar.com.pricealertapp.models.AlertSetUp
import java.util.concurrent.Executors

class MyRealTimeService : Service() {
    // Binder given to clients
    private val binder = LocalBinder()
    lateinit var connectionEstablisher: ConnectionEstablisher
    val es = Executors.newFixedThreadPool(5)
    private val connectionMap = mutableMapOf<AlertSetUp, RealTimeDataConnection>()


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyRealTimeService = this@MyRealTimeService
        fun setConnectionEstablished(connectionEstablisher: ConnectionEstablisher) {
            this@MyRealTimeService.connectionEstablisher = connectionEstablisher
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun testRealTimeGlobalQuote(alertSetUp: AlertSetUp, isCrypto:Boolean = false){
        lateinit var realTimeDataConnection: RealTimeDataConnection
        val priceObservable = object : PriceObservable {
            override fun newPrice(stockInfo: StockInfo) {
                val priceBigDecimal = stockInfo.last.price.toBigDecimal()
                if(priceBigDecimal > alertSetUp.tippingPoint) {
                    createNotification(getString(R.string.notification_update, stockInfo.symbol, priceBigDecimal.toString()), alertSetUp)
                }
            }

        }
        es.execute {
            realTimeDataConnection = RealTimeDataConnection()
            if(!isCrypto){
//                realTimeDataConnection.startRealTimeStockPriceListening(alertSetUp.symbol, "MGM4OTI2ZmYtNWYzOS00MGU5LTg5MzMtYjE4N2UwZDVjZWNl", "JKI0YSQE2ORV3KCL")
                realTimeDataConnection.startRealTimeStockPriceListening(alertSetUp.symbol, getString(R.string.STREAM_IO_APP_TOKEN), getString(R.string.POLYGON_IO_API_KEY))

            }else{
                realTimeDataConnection.startRealTimeCryptoPriceInUSDListening(alertSetUp.symbol, getString(R.string.STREAM_IO_APP_TOKEN), getString(R.string.APLHA_ADVANTAGE_API_KEY))
            }
            realTimeDataConnection.connect(priceObservable)
            connectionMap[alertSetUp] = realTimeDataConnection
        }
    }

    private fun createNotification(text: String, alertSetUp: AlertSetUp){
        createNotificationChannel(alertSetUp.symbol)
        val builder = NotificationCompat.Builder(this, alertSetUp.symbol)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.stock_price_alert))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(alertSetUp.notificationId, builder.build())
        }
    }

    private fun createNotificationChannel(id: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.foreground_notification)
            val descriptionText = getString(R.string.foreground_notification_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun disableAllAlerts(){
        connectionMap.forEach { t, u ->
            if(u.isConnected)
                u.disconnect()
        }
    }

    fun createNewAlerts(list: List<AlertSetUp>){
        list.forEach {
            testRealTimeGlobalQuote(it)
        }
    }

    fun createNewAlert(alertSetUp: AlertSetUp, isCrypto: Boolean = false){
        testRealTimeGlobalQuote(alertSetUp, isCrypto)
    }

    fun testMyservice() {
        val str = connectionMap.toString()
        print(str)
    }

    fun getNextNotificationId() = connectionMap.size
}
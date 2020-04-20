package kedar.com.pricealertapp.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kedar.AlertApplication
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.Magnitude

class MainActivity : AppCompatActivity() {
    var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        if(AlertApplication.instance.mBound){
//            startListening()
//        }
    }



    private fun startListening(){
        AlertApplication.instance.mService.createNewAlerts(mutableListOf(AlertSetUp("AAPL", 230.toBigDecimal(), Magnitude.POSITIVE, 1)
                , AlertSetUp("MSFT", 160.toBigDecimal(), Magnitude.POSITIVE, 2),
                AlertSetUp("BA", 130.toBigDecimal(), Magnitude.POSITIVE, 3)))
    }

//    private lateinit var mService: MyRealTimeService
//    private var mBound: Boolean = false
//
//    private val connectionEstablisher = object : ConnectionEstablisher{
//        override fun connectionStarted(data: LiveData<String>) {
//            data.observe(this@MainActivity, Observer {
//                price -> tv!!.text = price
//                val priceInBigDecimal = price?.toBigDecimalOrNull()
//                if(priceInBigDecimal != null ){
//                    if(priceInBigDecimal > 7100.000.toBigDecimal()){
//                        createNotification("Price of BTC is now $priceInBigDecimal")
//                    }
//                }
//            })
//        }
//    }
//
//    /** Defines callbacks for service binding, passed to bindService()  */
//    private val connection = object : ServiceConnection {
//
//        override fun onServiceConnected(className: ComponentName, service: IBinder) {
//            // We've bound to LocalService, cast the IBinder and get LocalService instance
//            val binder = service as MyRealTimeService.LocalBinder
//            mService = binder.getService()
//            binder.setConnectionEstablished(connectionEstablisher)
//            mBound = true
//            startListening()
//        }
//
//        override fun onServiceDisconnected(arg0: ComponentName) {
//            mBound = false
//        }
//    }

//    override fun onStop() {
//        super.onStop()
//        unbindService(connection)
//        mBound = false
//    }
//
//
//    override fun onStart() {
//        super.onStart()
//        // Bind to LocalService
//        Intent(this, MyRealTimeService::class.java).also { intent ->
//            bindService(intent, connection, Context.BIND_AUTO_CREATE)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        tv = findViewById(R.id.price)
//    }
//
//    private fun startListening(){
//        mService.createNewAlerts(mutableListOf(AlertSetUp("AAPL", 230.toBigDecimal(), Magnitude.POSITIVE, 1)
//        , AlertSetUp("MSFT", 160.toBigDecimal(), Magnitude.POSITIVE, 2),
//                AlertSetUp("BA", 130.toBigDecimal(), Magnitude.POSITIVE, 3)))
//    }
//
//    private fun createNotification(text: String){
//        createNotificationChannel()
//        val builder = NotificationCompat.Builder(this, "stock_notification_alert")
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Stock price alert")
//                .setContentText(text)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)) {
//            // notificationId is a unique int for each notification that you must define
//            notify(52, builder.build())
//        }
//    }
//
//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Stock notification channel"
//            val descriptionText = "This is where you get up to date stock info."
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel("stock_notification_alert", name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

}
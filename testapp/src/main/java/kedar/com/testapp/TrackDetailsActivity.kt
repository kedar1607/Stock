package kedar.com.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import kedar.AlertApplication
import kedar.com.testapp.models.AlertSetUp
import kedar.com.testapp.models.Magnitude
import kotlinx.android.synthetic.main.activity_track_details.*

class TrackDetailsActivity : AppCompatActivity() {


    private var isPriceEntered = false

    private var isSymbolEntered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_details)
//        button = findViewById(R.id.btn_start)

        readStockSymbol()
        readHitPrice()
//        button.setOnClickListener { startListening() }
        btn_test.setOnClickListener { trackStock() }
        btn_disable.setOnClickListener {
            AlertApplication.instance.mService.disableAllAlerts()
        }
    }

    private fun readStockSymbol() {
        et_symbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isSymbolEntered = !s.isNullOrBlank()
                btn_test.isEnabled =  isSymbolEntered && isPriceEntered
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun readHitPrice() {
        et_price.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isPriceEntered = !s.isNullOrBlank() && s.toString().toBigDecimalOrNull()!= null
                btn_test.isEnabled =   isPriceEntered && isSymbolEntered
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun trackStock(){
        AlertApplication.instance.mService.createNewAlert(AlertSetUp(
                symbol = et_symbol.text.toString(),
                tippingPoint = et_price.text.toString().toBigDecimal(),
                magnitude = Magnitude.POSITIVE,
                notificationId = AlertApplication.instance.mService.getNextNotificationId()
        ), cb_isCrypto.isChecked)
        et_price.text.clear()
        et_symbol.text.clear()
        btn_test.isEnabled = false
    }

    private fun testMyServiceAfterKill(){
//        AlertApplication.instance.mService.testMyservice()
    }

    private fun startListening(){
        AlertApplication.instance.mService.createNewAlerts(mutableListOf(AlertSetUp("AAPL", 230.toBigDecimal(), Magnitude.POSITIVE, 1)
                , AlertSetUp("MSFT", 160.toBigDecimal(), Magnitude.POSITIVE, 2),
                AlertSetUp("BA", 130.toBigDecimal(), Magnitude.POSITIVE, 3)))
    }
}

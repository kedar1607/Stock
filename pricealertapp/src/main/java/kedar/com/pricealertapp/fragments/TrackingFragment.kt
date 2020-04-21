package kedar.com.pricealertapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kedar.AlertApplication
import kedar.com.pricealertapp.R
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.Magnitude
import kotlinx.android.synthetic.main.fragment_tracking.*

class TrackingFragment: Fragment() {

    private var isPriceEntered = false

    private var isSymbolEntered = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        readStockSymbol()
        readHitPrice()
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


}
package kedar.com.pricealertapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kedar.com.pricealertapp.R
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.LiveUpdateStock
import kotlinx.android.synthetic.main.item_stock.view.*

class StockViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
            liveUpdateStock: LiveUpdateStock,
            onClick: ((LiveUpdateStock) -> Unit)
    ) {
        itemView.tag = liveUpdateStock
        itemView.tv_symbol.text = liveUpdateStock.symbol
        val livePrice = liveUpdateStock.livePrice
        itemView.tv_price.text = (if (livePrice != null) "$$livePrice" else itemView.context.getString(R.string.loading)).toString()
        itemView.btn_enable_disable.text = itemView.context.getString(R.string.enable_caps)
//        (alertSetUp.description != null).let {
//            itemView.tv_subtitle.setVisible(it)
//            if (it) {
//                itemView.tv_subtitle.text = itemView.context.getString(alertSetUp.description!!)
//            }
//        }
//
//        itemView.radio_button.isChecked = alertSetUp.selected

        itemView.setOnClickListener {
            onClick.invoke(liveUpdateStock)
        }
    }

}
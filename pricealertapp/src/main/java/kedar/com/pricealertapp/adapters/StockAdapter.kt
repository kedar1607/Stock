package kedar.com.pricealertapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kedar.com.pricealertapp.R
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.LiveUpdateStock

class StockAdapter(private val onClick: ((LiveUpdateStock) -> Unit)) : ListAdapter<LiveUpdateStock, StockViewHolder>(StockItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stockItem  = getItem(position)
        holder.bind(stockItem, onClick)
    }
}
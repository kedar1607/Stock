package kedar.com.pricealertapp.adapters

import androidx.recyclerview.widget.DiffUtil
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.LiveUpdateStock
import kedar.com.realtimestocks.models.StockInfo

class StockItemDiffCallBack : DiffUtil.ItemCallback<LiveUpdateStock>() {
    override fun areItemsTheSame(oldItem: LiveUpdateStock, newItem: LiveUpdateStock): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: LiveUpdateStock, newItem: LiveUpdateStock): Boolean {
        return oldItem == newItem && oldItem.livePrice == newItem.livePrice
    }
}
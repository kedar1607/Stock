package kedar.com.pricealertapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import kedar.AlertApplication
import kedar.com.pricealertapp.R
import kedar.com.pricealertapp.adapters.StockAdapter
import kedar.com.pricealertapp.models.AlertSetUp
import kedar.com.pricealertapp.models.LiveUpdateStock
import kedar.com.pricealertapp.viewmodels.StocksViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(){

    val viewModel: StocksViewModel by navGraphViewModels(R.id.nav_main) { defaultViewModelProviderFactory }

    val stockAdapter = StockAdapter{
        activateNewAlert(liveUpdateStock = it)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcv_stocks_active.adapter = stockAdapter
        viewModel.loadAllActiveStocks()
        viewModel.stocks.observe(viewLifecycleOwner, Observer {
            stockAdapter.submitList(it)
        })
    }

    private fun activateNewAlert(liveUpdateStock: LiveUpdateStock){

    }
}
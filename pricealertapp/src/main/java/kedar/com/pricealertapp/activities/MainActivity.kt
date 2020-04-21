package kedar.com.pricealertapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kedar.com.pricealertapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.mainNavFragment)
        val bottomTabs = HashSet<Int>().apply {
            add(R.id.navigation_track)
            add(R.id.navigation_dashboard)
        }
        appBarConfiguration = AppBarConfiguration.Builder(bottomTabs).setDrawerLayout(drawerLayout).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        hideBackButton()
        tabs.setupWithNavController(navController)
    }

    private fun hideBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

}
package com.atsistemas.formacion2022.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.atsistemas.formacion2022.R
import com.atsistemas.formacion2022.common.BaseActivity
import com.atsistemas.formacion2022.databinding.ActivityScrollingBinding
import com.atsistemas.formacion2022.ui.main.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityScrollingBinding>(ActivityScrollingBinding::inflate) {

    private val vm by viewModel<HomeViewModel>()

    private val vmMain by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->

            vm.onActionDownloadClicked()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        observeData(vmMain.obsShowFab,::onObserveFab)
    }

    private fun onObserveFab(show: Boolean) {
        binding.fab.visibility = if(show) View.VISIBLE else View.GONE

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showLoading() {
        binding.flMainLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.flMainLoading.visibility = View.GONE
    }
}
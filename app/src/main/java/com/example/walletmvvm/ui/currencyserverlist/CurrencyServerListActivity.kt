package com.example.walletmvvm.ui.currencyserverlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.walletmvvm.R
import kotlinx.android.synthetic.main.activity_currencyserverlist.*

class CurrencyServerListActivity : AppCompatActivity() {

    private val mToolbar: Toolbar by lazy { toolbar_currencylist_currency }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currencyserverlist)

        firstSetup()
    }

    private fun firstSetup() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setCustomView(R.layout.toolbar_currencyserverlist)

        supportFragmentManager.beginTransaction()
            .add(R.id.coordinatorlayout_currencylist_container, CurrencyServerListFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
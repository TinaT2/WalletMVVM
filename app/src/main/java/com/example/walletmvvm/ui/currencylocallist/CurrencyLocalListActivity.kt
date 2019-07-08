package com.example.walletmvvm.ui.currencylocallist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.walletmvvm.R

class CurrencyLocalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currencylocallist)

        firstSetup()
    }

    private fun firstSetup() {
        val mToolber = findViewById<Toolbar>(R.id.toolbar_currencylist_currency)
        setSupportActionBar(mToolber)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setCustomView(R.layout.toolbar_currencyserverlist)

        supportFragmentManager.beginTransaction()
            .add(R.id.coordinatorlayout_currencylist_container, CurrencyLocalListFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
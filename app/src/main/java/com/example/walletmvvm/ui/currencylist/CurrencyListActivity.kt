package com.example.walletmvvm.ui.currencylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.walletmvvm.R

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currencylist)

        firstSetup()
    }

    private fun firstSetup() {
        val mToolber = findViewById<Toolbar>(R.id.toolbar_currencylist_currency)
        setSupportActionBar(mToolber)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setCustomView(R.layout.toolbar_currencylist)

        supportFragmentManager.beginTransaction()
            .add(R.id.coordinatorlayout_currencylist_container, CurrencyListFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
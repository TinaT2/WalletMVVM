package com.example.walletmvvm.ui.currencylocallist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walletmvvm.R
import kotlinx.android.synthetic.main.activity_currencylocallist.*

class CurrencyLocalListActivity : AppCompatActivity() {

    private val mToolber by lazy { toolbar_currencylist_currency }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currencylocallist)

        firstSetup()
    }

    private fun firstSetup() {
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
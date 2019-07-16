package com.example.walletmvvm.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.walletmvvm.R
import com.example.walletmvvm.ui.currencylocallist.CurrencyLocalListActivity
import com.example.walletmvvm.ui.currencyserverlist.CurrencyServerListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUiComponents()
        Log.v("appSenario", "MainActivity create")

    }

    private fun initUiComponents() {
        button_currencyserverlist.setOnClickListener {
            val intent = Intent(this, CurrencyServerListActivity::class.java)
            startActivity(intent)
        }
        button_currencylocallist.setOnClickListener {
            val intent = Intent(this, CurrencyLocalListActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.walletmvvm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walletmvvm.R
import com.example.walletmvvm.ui.currencylist.CurrencyListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUiComponents()
    }

    private fun initUiComponents(){
        val intent = Intent(this,CurrencyListActivity::class.java)
        startActivity(intent)
    }
}
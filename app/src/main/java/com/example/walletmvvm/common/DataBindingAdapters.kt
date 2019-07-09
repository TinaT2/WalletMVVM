package com.example.walletmvvm.common

import android.view.View
import androidx.databinding.BindingAdapter


object DataBindingAdapters {

    @BindingAdapter("app:hideProgressBar")
    @JvmStatic
    fun hideProgressBar(view: View, listSize: Int) {
        view.visibility = if (listSize == 0) View.VISIBLE else View.INVISIBLE
    }
}
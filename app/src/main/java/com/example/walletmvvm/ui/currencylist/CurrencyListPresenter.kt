package com.example.walletmvvm.ui.currencylist

import android.annotation.SuppressLint
import com.example.walletmvvm.WalletMvvmApplication
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.remote.APIClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyListPresenter(val currencyListView: CurrencyListContract.View) :
    CurrencyListContract.Presenter {
    //override var currencyListDatabase = emptyList<CurrencyModel>()
    override var currencyListServer: List<CurrencyModel>? = null
//    private val currencyDao: CurrencyDao =
//        WalletRoomDatabase.getDatabase(WalletMvvmApplication.instance.applicationContext).currencyDao()


    @SuppressLint("CheckResult")
    override fun getCurrencyListFromServer() {



        val interfaceApi = APIClient.getService()
//
//        interfaceApi?.currencyList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(DisposableSingleObserver<List<CurrencyModel>>{
//
//
//
//
//
//            })

        interfaceApi?.currencyList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(currencyListView.getData())







//        APIClient.getService()?.currencyList()?.enqueue(object : Callback<List<CurrencyModel>> {
//            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
//                val result = "failed: " + t.message
//                currencyListView.showResult(result, false)
//            }
//
//            override fun onResponse(call: Call<List<CurrencyModel>>, response: Response<List<CurrencyModel>>) {
//
//
//
//                var currencyList=response.body()
//
//
//
//
//
//
//                val result = "responsed: " + response.message()
//                currencyListView.showResult(result, false)
//                currencyListServer = response.body()
//                if (!currencyListServer.isNullOrEmpty()) {
//                    currencyListView.setRecyclerData(currencyListServer!!)
//                    currencyListView.visibleAddButton()
//
//
//                }
//            }
//
//        })
    }

//
//    override fun insertCurrencyToDatabase(currencyModel: CurrencyModel): Long {
//        return InsertCurrencyAsyncTask(currencyDao).execute(currencyModel).get()
//    }

//    override fun insertCurrencyListToDatabase() {
//        for (currency in currencyListServer!!) {
//            InsertCurrencyAsyncTask(currencyDao).execute(currency)
//        }
//        currencyListView.showResult(("items added"),
//            false)
//    }

    // //Async Tasks___________________________________
//    private class InsertCurrencyAsyncTask(val currencyDao: CurrencyDao) :
//        AsyncTask<CurrencyModel, Void, Long>() {
//        override fun doInBackground(vararg currencyModel: CurrencyModel?): Long {
//
//            return currencyModel[0]?.let { currencyDao.insert(it) } ?: 0L
//        }
//    }

//    private class GetCurrencyListAsyncTask(val currencyDao: CurrencyDao) :
//        AsyncTask<Void, Void, List<CurrencyModel>>() {
//        override fun doInBackground(vararg params: Void?): List<CurrencyModel> {
//            return currencyDao.getCurrencyList()
//        }

    // }


}



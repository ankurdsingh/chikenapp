package com.android.chickenapp.viewmodel

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.chickenapp.R
import com.android.chickenapp.model.ProductModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val _productModel = MutableLiveData<ProductModel>()
    val productModel : LiveData<ProductModel>
        get() = _productModel
    val app = application
    fun getProductDetails(){
        launch {
            val response = getProductDetails("product_reorder.json")
            response?.let {
                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<ProductModel> =
                    moshi.adapter<ProductModel>(ProductModel::class.java)
                val productModel: ProductModel? = jsonAdapter.fromJson(response)
                productModel?.let {
                    _productModel.postValue(productModel)
                }
            }
        }
    }


    private suspend fun getProductDetails(filename : String):String?{

        val jsonString: String
        try {
            jsonString = app.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}
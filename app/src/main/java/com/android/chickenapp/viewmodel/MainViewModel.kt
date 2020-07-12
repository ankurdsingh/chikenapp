package com.android.chickenapp.viewmodel

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.chickenapp.R
import com.android.chickenapp.model.ProductDataProductsModel
import com.android.chickenapp.model.ProductModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val _productModel = MutableLiveData<ProductModel>()
    val productModel: LiveData<ProductModel>
        get() = _productModel
    private val _products = MutableLiveData<List<ProductDataProductsModel>>()
    val products: LiveData<List<ProductDataProductsModel>>
        get() = _products
    val app = application
    /**
     * this method will load the file and read the data
     * then convert this data to objects and post it via
     * live data [_productModel]
     *
     */
    fun getProductDetails() {
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

    /**
     * co-routine execution function to read data from json file
     *
     * @param filename name of local json response file
     * @return Json format string
     */
    private suspend fun getProductDetails(filename: String): String? {

        val jsonString: String
        try {
            jsonString = app.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    /**
     * filter list will filter result based on type of delivery Type
     *
     * @param key to identify param of filter
     * @param allProducts list all products
     */
    fun filterList(key: String?, allProducts: List<ProductDataProductsModel>) {
        key?.let {
            if (it.equals("express", ignoreCase = true)) {
                val filteredList: ArrayList<ProductDataProductsModel> = arrayListOf()
                allProducts.forEach { product ->
                    if (product.productMerchandise.deliveryType.equals(it, ignoreCase = true)) {
                        filteredList.add(product)
                    }
                }
                _products.postValue(filteredList)
            }
        }
    }

}
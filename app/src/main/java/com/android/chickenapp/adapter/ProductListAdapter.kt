package com.android.chickenapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.chickenapp.R
import com.android.chickenapp.databinding.LayoutProductItemBinding
import com.android.chickenapp.model.ProductDataProductsModel
import com.android.chickenapp.ui.ProductItemViewHolder

class ProductListAdapter(private val products: ArrayList<ProductDataProductsModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil
            .inflate<LayoutProductItemBinding>(inflater,
                R.layout.layout_product_item,
                parent,false)
        return ProductItemViewHolder(view)

    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductItemViewHolder){
            holder.view.product = products[position]
        }
    }

    fun update(products: List<ProductDataProductsModel>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}
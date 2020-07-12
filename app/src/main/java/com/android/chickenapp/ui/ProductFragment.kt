package com.android.chickenapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.chickenapp.R
import com.android.chickenapp.adapter.ProductListAdapter
import com.android.chickenapp.databinding.FragmentProductBinding
import com.android.chickenapp.model.ProductDataProductsModel
import com.android.chickenapp.utill.RecyclerViewDividerUtill
import com.android.chickenapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_product.*


/**
 * A simple [Fragment] subclass.
 * to handle interaction events.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val FILTER_BY = "filter_by"
class ProductFragment : Fragment() {
    private lateinit var dataBinding: FragmentProductBinding
    private var filterParam: String? = null
    private var mainViewModel : MainViewModel? = null
    private var productList : ArrayList<ProductDataProductsModel> = ArrayList()
    private val productListAdapter : ProductListAdapter= ProductListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filterParam = it.getString(FILTER_BY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_product, container, false)
        return  dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModel()
    }
    private fun init(){
        mainViewModel = activity?.let{
            ViewModelProvider(it).get(MainViewModel::class.java)
        }
        rvProductList.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = productListAdapter
            addItemDecoration( RecyclerViewDividerUtill(
                    resources.getDimensionPixelSize(R.dimen.photos_list_spacing)))
        }
    }

    private fun observeViewModel(){
        mainViewModel?.let{viewModel->
            viewModel.productModel.observe(this, Observer {product->
                dataBinding.dataInfo = product.data
                if(filterParam!=null && filterParam.equals("express",ignoreCase = true)) {
                    viewModel.filterList(filterParam, product.data.products)
                }else {
                    productList.addAll(product.data.products)
                    productListAdapter.update(productList)
                    dataBinding.count = productList.size
                }
            })
            viewModel.products.observe(this, Observer { products->
                products?.let {
                    productList.addAll(it)
                    productListAdapter.update(productList)
                    dataBinding.count = productList.size
                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ProductFragment.
         */
        @JvmStatic
        fun newInstance(filterBy: String) = ProductFragment()
            .apply {
                arguments = Bundle().apply {
                    putString(FILTER_BY,filterBy)
                }
            }
    }
}

package com.android.chickenapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.android.chickenapp.R
import com.android.chickenapp.adapter.TabsAdapter
import com.android.chickenapp.databinding.ActivityMainBinding
import com.android.chickenapp.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var activityMainBinding :  ActivityMainBinding
    private lateinit var tabsAdapter: TabsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        activityMainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.productModel.observe(this, Observer {
            it?.data?.let {productDataModel->
                activityMainBinding.favourite = productDataModel
                tabsAdapter.update(productDataModel.filters)
            }
            Log.d("TAG",it.toString())
        })
    }

    private fun init(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewPager = findViewById<ViewPager>(R.id.pager)
        tabsAdapter = TabsAdapter(supportFragmentManager)
        viewPager.apply {
            adapter = tabsAdapter
        }
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getProductDetails()

    }
}

package com.android.chickenapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.android.chickenapp.R
import com.android.chickenapp.adapter.TabsAdapter
import com.android.chickenapp.databinding.ActivityMainBinding
import com.android.chickenapp.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

private const val TIME_TO_EXIT = 2000

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var tabsAdapter: TabsAdapter
    private var lastPressedTime: Long = 0
    private lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil
            .setContentView(this,R.layout.activity_main)
        init()
        observeViewModel()

    }


    private fun observeViewModel() {
        viewModel.productModel.observe(this, Observer {
            it?.data?.let {productDataModel->
                activityMainBinding.favourite = productDataModel
                tabsAdapter.update(productDataModel.filters)
                viewPager.adapter = tabsAdapter
            }
            Log.d("TAG",it.toString())
        })
    }

    private fun init(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewPager = findViewById(R.id.pager)
        tabsAdapter = TabsAdapter(supportFragmentManager)
        /*viewPager.apply {
            adapter = tabsAdapter
        }*/
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getProductDetails()
    }

    // Home Back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Double back to exit
    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastPressedTime < TIME_TO_EXIT) {
            super.onBackPressed()
        } else {
            Toast.makeText(
                applicationContext, getString(R.string.press_again_to_exit),
                Toast.LENGTH_SHORT
            ).show()
            lastPressedTime = System.currentTimeMillis()
        }
    }
}

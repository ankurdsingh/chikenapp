package com.android.chickenapp.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.chickenapp.model.ProductDataFilter
import com.android.chickenapp.ui.ProductFragment

class TabsAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var filter :ArrayList<ProductDataFilter> = mutableListOf<ProductDataFilter>() as ArrayList<ProductDataFilter>


    override fun getItem(position: Int): Fragment {
        val fragment = ProductFragment.newInstance()
        return fragment
    }

    override fun getCount(): Int {
        Log.d("TAG", "size ${filter.size}")
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {

        //return filter[position].title
        return when(position){
            0->"ABC"
            else-> "DEF"
        }

    }

    fun update(filter :List<ProductDataFilter>){
        this.filter.clear()
        Log.d("TAG", this.filter.toString())
        this.filter.addAll(filter)
        Log.d("TAG", this.filter.toString())
        notifyDataSetChanged()
    }

}
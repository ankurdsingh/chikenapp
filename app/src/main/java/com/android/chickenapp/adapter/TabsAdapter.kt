package com.android.chickenapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.chickenapp.model.ProductDataFilter
import com.android.chickenapp.ui.ProductFragment

class TabsAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var filter :ArrayList<ProductDataFilter> = mutableListOf<ProductDataFilter>() as ArrayList<ProductDataFilter>


    override fun getItem(position: Int): Fragment = ProductFragment.newInstance(filter[position].type)


    override fun getCount(): Int {
//        Log.d("TAG", "size ${filter.size}")
        return filter.size
    }

    override fun getPageTitle(position: Int): CharSequence {

        return filter[position].title
    }

    fun update(filter :List<ProductDataFilter>){
        this.filter.clear()
//        Log.d("TAG", this.filter.toString())
        this.filter.addAll(filter)
//        Log.d("TAG", this.filter.toString())
        notifyDataSetChanged()
    }

}
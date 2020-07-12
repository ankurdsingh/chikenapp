package com.android.chickenapp.utill

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.chickenapp.R
import java.lang.StringBuilder
import kotlin.math.roundToInt

@BindingAdapter("android:currency")
fun setPrice(view :TextView, amount : Double){
    val amountWithCurrency = StringBuilder()
    amountWithCurrency.append("\u20B9 ")
    amountWithCurrency.append(amount.roundToInt())
    view.text = amountWithCurrency.toString()
}

@BindingAdapter("android:weight")
fun setWeight(view :TextView, weight : String){
    val weightModified = StringBuilder()
    weightModified.append(view.context.getString(R.string.net_weight))
    weightModified.append(" ")
    if (weight == ""){
        weightModified.append("NA")
    }else {
        weightModified.append(weight)
    }
    view.text = weightModified.toString()
}
@BindingAdapter("android:count")
fun setItemCount(view :TextView, count : Int){
    val weightModified = StringBuilder()
    weightModified.append("(")
    weightModified.append(count)
    weightModified.append(" ")
    weightModified.append(view.context.getString(R.string.items))
    weightModified.append(")")

    view.text = weightModified.toString()
}
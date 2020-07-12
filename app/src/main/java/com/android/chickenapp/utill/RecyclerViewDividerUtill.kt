package com.android.chickenapp.utill

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDividerUtill(private val mSizeGridSpacingPx: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        if(parent.getChildAdapterPosition(view)%2 ==0)
        outRect.left = mSizeGridSpacingPx

        outRect.right = mSizeGridSpacingPx
        outRect.bottom = mSizeGridSpacingPx

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = mSizeGridSpacingPx
        } else {
            outRect.top = 0
        }
    }
}
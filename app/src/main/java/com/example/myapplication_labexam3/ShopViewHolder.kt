package com.example.myapplication_labexam3

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ShopViewHolder(view: View): ViewHolder(view) {

    val cbItem:CheckBox = view.findViewById(R.id.cbItem)
    val ivDelete:ImageView= view.findViewById(R.id.ivDelete)

}
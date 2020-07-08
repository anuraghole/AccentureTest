package com.example.accenturetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.accenturetest.R
import com.example.accenturetest.data.Row
import kotlinx.android.synthetic.main.layout_item_row.view.*

class RowAdapter(var context: Context, var rowList: List<Row>) :
    RecyclerView.Adapter<RowAdapter.RowViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowAdapter.RowViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_row, parent, false)
        return RowViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RowAdapter.RowViewHolder, position: Int) {
        //holder.bindItems(rowList[position])
        var item = rowList[position]
        holder.itemView.apply {
            //if title is blank or null set default value NA
            if (item.title.isNullOrBlank()){
                item.title="N.A."
            }else{
                tvRowTitle.text = item.title
            }
            if (item.description.isNullOrBlank()){
                item.description="N.A."
            }else{
                tvRowDescription.text = item.description
            }
        }
        // if image url is null or wrong image url load the placeholder icon
        Glide.with(context).load(item.imageHref).error(R.drawable.ic_placeholder)
            .into(holder.itemView.ivRowImage);

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return rowList.size
    }

    //the class is hodling the list view
    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
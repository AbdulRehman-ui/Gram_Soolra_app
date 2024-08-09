package com.project.gramsoolra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.gramsoolra.R
import com.project.gramsoolra.dataClass.ProductListItem

class ProductAdapter (private val productList : List<ProductListItem>) : RecyclerView.Adapter<ProductAdapter.productViewHolder>() {

    inner class productViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val productImage : ImageView = itemView.findViewById(R.id.productImage)
        val productName : TextView = itemView.findViewById(R.id.productName)
        val productPrice : TextView = itemView.findViewById(R.id.productPrice)
        val productLocation : TextView = itemView.findViewById(R.id.productLocation)
        val productItem : CardView = itemView.findViewById(R.id.productItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_item_design, parent, false)
        return productViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
            val products = productList[position]

        val context = holder.productItem.context
        Glide.with(context).load(products.productImage).into(holder.productImage)

        holder.productName.text = products.productName
        holder.productPrice.text = products.productPrice
        holder.productLocation.text = products.productLocation

    }

}
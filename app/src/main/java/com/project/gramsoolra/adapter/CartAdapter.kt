package com.project.gramsoolra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.gramsoolra.R
import com.project.gramsoolra.dataClass.CartListItem

class CartAdapter(private val cartList: MutableList<CartListItem>, private val onListEmpty: () -> Unit) : RecyclerView.Adapter<CartAdapter.cartViewHolder>() {

    inner class cartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView = itemView.findViewById(R.id.productImage)
        val productName : TextView = itemView.findViewById(R.id.productName)
        val productPrice : TextView = itemView.findViewById(R.id.productPrice)
        val removeButton : ImageView = itemView.findViewById(R.id.removeIcon)
        val cartItems : ConstraintLayout = itemView.findViewById(R.id.cartItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return cartViewHolder(v)
    }

    override fun getItemCount(): Int {
       return cartList.size
    }

    override fun onBindViewHolder(holder: cartViewHolder, position: Int) {
        val cartItems = cartList[position]

        val context = holder.cartItems.context

        Glide.with(context).load(cartItems.productImage).into(holder.productImage)
        holder.productName.text = cartItems.productName
        holder.productPrice.text = cartItems.productPrice

        holder.removeButton.setOnClickListener {
            removeItem(position)
        }
    }

    fun removeItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartList.size)

        if (cartList.isEmpty()) {
            onListEmpty()
        }

    }

}
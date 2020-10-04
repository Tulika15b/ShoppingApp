package com.example.shoppingapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CardviewProductItemBinding
import com.example.shoppingapp.model.Product
import kotlinx.android.synthetic.main.cardview_product_item.view.*
import kotlinx.android.synthetic.main.item_cart_product_list.view.*
import kotlinx.android.synthetic.main.item_cart_product_list.view.cart_product_name

class ProductRVAdapter(val mContext : Context,
                       val cartItems: List<Product>) : RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>() {

    private var productList: List<Product>? = null

    override fun getItemCount() = productList?.size ?: 0

    fun setProducts(profiles: List<Product>) {
        this.productList = profiles
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mContext = itemView.context
        fun bindItem(cartItem: Product) {

            // This displays the cart item information for each item
            Glide.with(mContext).load(cartItem.prodImageUrl).into(itemView.image_view_product)
            itemView.card_view_product_name.text = cartItem.prodName
            itemView.card_view_product_price.text = "$" + cartItem.prodPrice
            itemView.tag = cartItem.prodId
        }

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(cartItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val parentView = LayoutInflater.from(mContext).inflate(R.layout.cardview_product_item, parent, false)
        return ProductViewHolder(parentView)
    }

}
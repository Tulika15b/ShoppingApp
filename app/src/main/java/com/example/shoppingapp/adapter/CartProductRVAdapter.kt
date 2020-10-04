package com.example.shoppingapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Product
import kotlinx.android.synthetic.main.item_cart_product_list.view.*

class CartProductRVAdapter(
    val mContext: Context,
    val cartItems: List<CartProductModel>
)
    : RecyclerView.Adapter<CartProductRVAdapter.ProductItemViewHolder>(){

    class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mContext = itemView.context
        fun bindItem(cartItem: CartProductModel) {

            // This displays the cart item information for each item
            Log.d("Cart", cartItem.cart_prod_name);
            Glide.with(mContext).load(cartItem.cart_prod_img_url).into(itemView.cart_product_iv)
            itemView.cart_product_name.text = cartItem.cart_prod_name
            itemView.cart_product_price.text = "$" + cartItem.cart_prod_price
            itemView.product_quantity.text = "Qty: " + cartItem.cart_prod_qty.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val parentView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_product_list, parent, false)
        return ProductItemViewHolder(parentView)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindItem(cartItems[position])

    }
}
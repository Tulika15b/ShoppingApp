package com.example.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.model.Category
import kotlinx.android.synthetic.main.item_catgeory_list.view.*

class CategoryRVAdapter(val categories : List<Category>?, private val clickListener: (Category) -> Unit)
    : RecyclerView.Adapter<CategoryRVAdapter.CategoryItemViewHolder>(){

    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryBtn = itemView.category_name as Button

        fun bind(category: Category?, clickListener: (Category) -> Unit) {
            if (category != null) {
                categoryBtn.text = category.categoryName
                //itemView.setOnClickListener { clickListener(category)}
            };

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val parentView = LayoutInflater.from(parent.context).inflate(R.layout.item_catgeory_list, parent, false)
        return CategoryItemViewHolder(parentView)
    }

    override fun getItemCount(): Int {
        return categories!!.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val item = categories?.get(position)
        holder.bind(item,clickListener)
        holder.categoryBtn.setOnClickListener(View.OnClickListener(){

            if (item != null) {
                clickListener(item)
            }
        });

    }
}
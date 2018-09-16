package com.example.leonardoallen.guiabolso.category

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.leonardoallen.guiabolso.R

class CategoryAdapter(var categories: List<String>, var callback: CategoryCallback): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.categoryName.text = categories[position]
        holder.cardView.setOnClickListener {
            callback.onCategoryClick(categories[position])
        }
    }

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var cardView: CardView = itemView.findViewById(R.id.cardView)
        var categoryName: TextView = itemView.findViewById(R.id.categoryName)

    }

    interface CategoryCallback {
        fun onCategoryClick(category: String)
    }
}
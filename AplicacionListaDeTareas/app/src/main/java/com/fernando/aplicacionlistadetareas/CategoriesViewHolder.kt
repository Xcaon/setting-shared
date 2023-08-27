package com.fernando.aplicacionlistadetareas

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: View = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, OnItemSelected: (Int) -> Unit){
        tvCategoryName.text = "Ejemplo"

        var color = if (taskCategory.isSelected){
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        viewContainer.setBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener {
            OnItemSelected(layoutPosition)
        }

        when (taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                    )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Other"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
        }

    }



}
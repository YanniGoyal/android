package com.example.experiment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.experiment.MainActivity.Companion.saveList
import com.example.experiment.MainActivity.Companion.selectText
import com.example.experiment.R

class TodoAdapter(private val todos: MutableList<String>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var lastSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = holder.adapterPosition
        val todo = todos[pos]
        holder.radioButton.text = todo
        holder.radioButton.setChecked(lastSelectedPosition == pos)
        holder.radioButton.setOnClickListener {
            lastSelectedPosition = pos
            selectText(holder.radioButton.text.toString())
            notifyDataSetChanged()
        }
        holder.delete.setOnClickListener {
            todos.remove(holder.radioButton.text.toString())
            saveList(holder.delete.context, todos)
            notifyDataSetChanged()
        }
        saveList(holder.delete.context, todos)
    }

    override fun getItemCount(): Int {
        return todos.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.item)
        val delete: Button = itemView.findViewById(R.id.deleteTodo)
    }
}
package com.charleston.espressocustomassertion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<ItemModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ListViewHolder).bind(list[position])

    fun addItems(list: List<ItemModel>) {
        this.list.let {
            it.clear()
            it.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemModel: ItemModel) {
            itemView.let {
                it.findViewById<TextView>(R.id.textView).text = itemModel.name

                if (itemModel.age == null) {
                    it.findViewById<TextView>(R.id.textView2).visibility = View.GONE
                } else {
                    it.findViewById<TextView>(R.id.textView2).visibility = View.VISIBLE
                    it.findViewById<TextView>(R.id.textView2).text = itemModel.age.toString()
                }
            }
        }
    }
}
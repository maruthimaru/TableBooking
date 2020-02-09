package com.cs442.dsuraj.quantumc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs442.dsuraj.quantumc.MenuItemAdapter.MyViewHolder
import java.util.*

class MenuItemAdapter(var context: Context, var menuItemModelArrayList: ArrayList<MenuItemModel>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.menu_item, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val menuItemModel = menuItemModelArrayList[i]
        myViewHolder.imageViewItem.setImageResource(menuItemModel.itemImage)
        myViewHolder.textviewItem.text = menuItemModel.itemName
    }

    override fun getItemCount(): Int {
        return menuItemModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewItem: ImageView
        var textviewItem: TextView

        init {
            imageViewItem = itemView.findViewById(R.id.imageViewItem)
            textviewItem = itemView.findViewById(R.id.textviewItem)
        }
    }

}
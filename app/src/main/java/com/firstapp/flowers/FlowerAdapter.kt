package com.firstapp.flowers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.flower_item.view.*

class FlowerAdapter(private val flowers:ArrayList<Flowers>):RecyclerView.Adapter<FlowerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flower_item,parent,false))
    }

    override fun onBindViewHolder(holder: FlowerAdapter.ViewHolder, position: Int) {
        val flower:Flowers = flowers[position]
        holder.title.text = flower.title
        holder.desc.text = flower.desc
        holder.price.text = flower.price
        Glide.with(holder.itemView.context).load(flower.img).into(holder.img)
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"${flower.title} added succesfuly", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return flowers.size

    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder (itemView){
        val img = itemView.flowerImage
        val title = itemView.title
        val desc = itemView.desc
        val price = itemView.price
    }

}
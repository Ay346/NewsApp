package com.example.newsapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.model.Source
import com.squareup.picasso.Picasso

internal class SourceAdapter(private var itemsList: ArrayList<Source>,
    private val clickAction : (Source)->(Unit)) :
    RecyclerView.Adapter<SourceAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView = view.findViewById(R.id.sourceId)
        var nameTextView: TextView = view.findViewById(R.id.sourceName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_sorce, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.idTextView.text = "id= "+item.id
        holder.nameTextView.text = "name= "+item.name
        holder.itemView.setOnClickListener{clickAction(item)}
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}
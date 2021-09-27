package com.bignerdranch.android.bullsandcowsapp.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bullsandcowsapp.R

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bulls: TextView = itemView.findViewById(R.id.bulls)
        private val cows: TextView = itemView.findViewById(R.id.cows)
        val guess: TextView = itemView.findViewById(R.id.guess)
        fun bind(item: Res) {
            bulls.text = item.bulls.toString()
            cows.text = item.cows.toString()
            guess.text = item.guess
        }
    }

    var data = ArrayList<Res>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item_view, parent, false)
        return ViewHolder(view)
    }

    fun addRes(res: Res) {
        data.add(res)
        notifyDataSetChanged()
    }

}
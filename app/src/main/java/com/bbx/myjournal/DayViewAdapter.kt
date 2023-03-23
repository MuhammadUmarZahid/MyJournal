package com.bbx.myjournal

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayViewAdapter (context: Context, list:EmotionDataByDayModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val context: Context = context
    var list: EmotionDataByDayModel = list

    private inner class HeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var entries: TextView = itemView.findViewById(R.id.entries)
        var date: TextView = itemView.findViewById(R.id.date)
        var layout: LinearLayout = itemView.findViewById(R.id.mainLayout)
        fun bind(position: Int) {
            val dateItem = list
            date.text = dateItem.date.toString()
            layout.setBackgroundColor(Color.LTGRAY)
            entries.text = "${dateItem.count} entries"
            //message.text = recyclerViewModel.textData
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return HeaderViewHolder(
                LayoutInflater.from(context).inflate(R.layout.subheader_item, parent, false)
            )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as HeaderViewHolder).bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

}
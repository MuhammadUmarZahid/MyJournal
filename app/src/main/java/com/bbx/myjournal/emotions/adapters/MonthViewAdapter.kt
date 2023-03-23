package com.bbx.myjournal.emotions.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbx.myjournal.R
import com.bbx.myjournal.data.EmotionDataByMonthModel

class MonthViewAdapter (context: Context, list: EmotionDataByMonthModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val context: Context = context
    var list: EmotionDataByMonthModel = list


    private inner class MonthViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var entries: TextView = itemView.findViewById(R.id.entries)
        var month: TextView = itemView.findViewById(R.id.month)
        var layout: LinearLayout = itemView.findViewById(R.id.mainView)
        fun bind(position: Int) {
            val monthItem = list
            entries.text = "${monthItem.count} entries"
            month.text = monthItem.month.toString()
            layout.setBackgroundColor(getEmotionColor(monthItem.sum))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MonthViewHolder(
                LayoutInflater.from(context).inflate(R.layout.header_item, parent, false)
            )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as MonthViewHolder).bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return 1;
    }

    fun getEmotionColor(type:Int): Int {
        if(type<0) {
            return Color.RED
        }
        else if(type==0) {
            return Color.YELLOW
        }
        else{
            return Color.GREEN
        }
    }
}
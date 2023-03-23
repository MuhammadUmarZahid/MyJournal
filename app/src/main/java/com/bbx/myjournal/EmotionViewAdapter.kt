package com.bbx.myjournal

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbx.myjournal.data.Emotion

class EmotionViewAdapter(context: Context, list:List<Emotion>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val context: Context = context
    var list: List<Emotion> = list

    private inner class EmotionViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById(R.id.time)
        var underline: View = itemView.findViewById(R.id.underline)
        var notes: TextView = itemView.findViewById(R.id.notes)
        fun bind(position: Int) {
            val emotionItem = list[position]
            time.text = emotionItem.time.toString()
            underline.setBackgroundColor(getEmotionColor(emotionItem.emotion_type?:0))
            notes.text = emotionItem.emotion_notes
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return EmotionViewHolder(
                LayoutInflater.from(context).inflate(R.layout.emotion_item, parent, false)
            )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as EmotionViewHolder).bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getEmotionColor(type: Int): Int {
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
package com.bbx.myjournal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bbx.myjournal.DatabaseConstants.EMOTION_TABLE
import java.util.*


@Entity(tableName = EMOTION_TABLE)
data class EmotionEntity(
    @PrimaryKey(autoGenerate = true)
    val emotionId :Int =0,
    @ColumnInfo(name = "emotion_type")
    val emotionType:Int,
    @ColumnInfo(name = "emotion_notes")
    val emotionNotes: String="",
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
){

    fun toDomain(): EmotionModel {
        val cal = Calendar.getInstance()
        cal.timeInMillis  = this.createdAt.time
        return EmotionModel(
            emotionType = this.emotionType,
            emotionNotes = this.emotionNotes.orEmpty(),
            createdAt = this.createdAt,
            month = cal.get(Calendar.MONTH),
            day = cal.get(Calendar.DAY_OF_YEAR),
        )
    }
}

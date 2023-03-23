package com.bbx.myjournal.data

import java.util.*

data class EmotionModel(
    val emotionType:Int,
    val emotionNotes: String="",
    val createdAt: Date,
    var month:Int?=-1,
    var day:Int?=-1
)

package com.bbx.myjournal

import java.util.*

data class EmotionModel(
    val emotionType:Int,
    val emotionNotes: String="",
    val createdAt: Date,
    var month:Int?=-1,
    var day:Int?=-1
)

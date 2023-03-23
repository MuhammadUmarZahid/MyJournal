package com.bbx.myjournal

import java.util.*

data class Emotion (
    var emotionId:Int = 0,
    var emotion_type: Int? = null,
    var emotion_notes: String? = null,
    var created_at: Date? = null,
    var time:String?=""
)
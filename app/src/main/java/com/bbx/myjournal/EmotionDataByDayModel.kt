package com.bbx.myjournal

import com.bbx.myjournal.data.Emotion

data class EmotionDataByDayModel(
    var day:Int? = 0,
    var count:Int = 0,
    var emotions: List<Emotion>? = null,
    var date:String?=""
){


}

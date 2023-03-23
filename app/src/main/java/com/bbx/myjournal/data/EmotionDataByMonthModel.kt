package com.bbx.myjournal.data

import com.bbx.myjournal.data.EmotionDataByDayModel

data class EmotionDataByMonthModel(
    var month: String? = null,
    var sum :Int= 0,
    var count:Int = 0,
    var days:List<EmotionDataByDayModel>?=null
){


}

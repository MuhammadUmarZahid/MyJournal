package com.bbx.myjournal

class EmotionDataByDayEntity (
    var day:Int = 0,
    var count:Int = 0
) {

    fun toModel(): EmotionDataByDayModel {
        return EmotionDataByDayModel(
            day =this.day,
            count = this.count
        )
    }


}

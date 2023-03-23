package com.bbx.myjournal

data class EmotionDataByMonthEntity(
    var month: String? = null,
    var sum :Int= 0,
    var count:Int = 0
){

    fun toModel(): EmotionDataByMonthModel {
        return EmotionDataByMonthModel(
            month = this.month,
            sum = this.sum,
            count = this.count,
        )
    }

}
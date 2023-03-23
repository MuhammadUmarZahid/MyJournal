package com.bbx.myjournal.emotions

import com.bbx.myjournal.data.EmotionDataByMonthModel

data class EmotionsUiState (
    val newItems:List<EmotionDataByMonthModel> = listOf()
)
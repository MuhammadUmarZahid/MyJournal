package com.bbx.myjournal.emotions

import androidx.lifecycle.ViewModel
import com.bbx.myjournal.data.EmotionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val emotionsRepository: EmotionsRepository): ViewModel(){

    private var _mainUiState = MutableStateFlow(EmotionsUiState())
    val mainUiState: StateFlow<EmotionsUiState> = _mainUiState.asStateFlow()

    suspend fun getEmotionsGroupedByMonthDay() {

            emotionsRepository.getEmotionsGroupedByMonthDay()
                .distinctUntilChanged()
                .collectLatest() { list ->
                        _mainUiState.update {
                            it.copy(newItems = list)
                        }
                }


    }


}
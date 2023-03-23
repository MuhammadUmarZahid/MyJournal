package com.bbx.myjournal.createemotion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbx.myjournal.data.EmotionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEmotionViewModel @Inject constructor(private val emotionsRepository: EmotionsRepository):ViewModel() {
    private val _createdUiState = MutableStateFlow(false)
    val createdState: StateFlow<Boolean> = _createdUiState.asStateFlow()

    fun onCreateEmotion(notes:String,emotion:Int){
        viewModelScope.launch {
            emotionsRepository.createEmotion(notes, emotion)
            _createdUiState.value = true
        }
    }

}
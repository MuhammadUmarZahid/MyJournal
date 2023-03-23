package com.bbx.myjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateEmotionActivity : AppCompatActivity() {
    private lateinit var createEMotionViewModel: CreateEmotionViewModel
    private var emotionType:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_emotion)
        createEMotionViewModel = ViewModelProvider(this)[CreateEmotionViewModel::class.java]

        val createBtn = findViewById<Button>(R.id.create)
        val notesInput = findViewById<TextInputEditText>(R.id.notes)
        val redBtn = findViewById<Button>(R.id.redBtn)
        val greenBtn = findViewById<Button>(R.id.greenBtn)
        val yellowBtn = findViewById<Button>(R.id.yellowBtn)

        redBtn.setOnClickListener {
            emotionType = -1
        }
        greenBtn.setOnClickListener {
            emotionType = 1
        }
        yellowBtn.setOnClickListener {
            emotionType = 0
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                createEMotionViewModel.createdState.collect {
                    // Update UI elements
                    if(it){
                        finish()
                    }
                }
            }
        }

        createBtn.setOnClickListener {
            if(notesInput.text.toString().isNotBlank()) {
                emotionType?.let { it1 ->
                    createEMotionViewModel.onCreateEmotion(
                        notesInput.text.toString(),
                        it1
                    )
                }
            }
        }
    }
}
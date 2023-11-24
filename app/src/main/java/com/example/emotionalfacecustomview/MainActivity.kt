package com.example.emotionalfacecustomview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.emotionalfacecustomview.customViews.EmotionalFaceView
import com.example.emotionalfacecustomview.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.happy.setOnClickListener {
            binding.emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
        }
        binding.sad.setOnClickListener {
            binding.emotionalFaceView.happinessState = EmotionalFaceView.SAD
        }
        binding.startShow.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                for (i in 0..100) {
                    delay(200)
                    Log.d("valuess", i.toString())
                    binding.textView2.text = i.toString()
                    binding.percentage.percentage = i
                    if (i>50){
                        binding.emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
                    }
                    else{
                        binding.emotionalFaceView.happinessState = EmotionalFaceView.SAD
                    }

                }
            }
        }


    }
}
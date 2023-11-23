package com.example.emotionalfacecustomview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.emotionalfacecustomview.customViews.EmotionalFaceView
import com.example.emotionalfacecustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.happy.setOnClickListener {
            binding.emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
        }
        binding.sad.setOnClickListener {
            binding.emotionalFaceView.happinessState = EmotionalFaceView.SAD
        }
    }
}
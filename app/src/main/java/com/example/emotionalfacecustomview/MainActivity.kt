package com.example.emotionalfacecustomview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.emotionalfacecustomview.customViews.EmotionalFaceView
import com.example.emotionalfacecustomview.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
                val job1 = async {
                    for (i in 0..100) {
                        delay(200)
                        binding.textView2.text = i.toString()
                        binding.percentage.labelText = "$i%"
                        if (i > 50) {
                            binding.emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
                        } else {
                            binding.emotionalFaceView.happinessState = EmotionalFaceView.SAD
                        }
                    }
                }

                val job2 = async {
                    for (i in 0..10000) {
                        delay(2)
                        Log.d("valuess", i.toString())
                        val fraction = i / 100f
                        binding.percentage.percentage = fraction.toFloat()

                    }
                }

                job1.await() // Wait for the first loop to complete
                job2.await() // Wait for the second loop to complete
            }
        }
    }
}
package com.kaliciak.neonmatch


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaliciak.neonmatch.databinding.ActivityGameScreenBinding

private lateinit var binding: ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        binding.testView.size = 100f

    }
}
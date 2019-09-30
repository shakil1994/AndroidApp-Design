package com.example.shakil.kotlinslidetounlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncorti.slidetoact.SlideToActView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slide_to_unlock.onSlideCompleteListener = object :SlideToActView.OnSlideCompleteListener{
            override fun onSlideComplete(view: SlideToActView) {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        slide_to_unlock.resetSlider()
    }
}

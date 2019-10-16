package com.example.shakil.kotlinanimatedsplashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animation_1 = AnimationUtils.loadAnimation(baseContext, R.anim.rotate)
        val animation_2 = AnimationUtils.loadAnimation(baseContext, R.anim.anti_rotate)
        val animation_3 = AnimationUtils.loadAnimation(baseContext, R.anim.abc_fade_out)

        img_logo.startAnimation(animation_2)

        animation_2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                img_logo.startAnimation(animation_1)
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

        animation_1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                img_logo.startAnimation(animation_3)
                finish()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
    }
}

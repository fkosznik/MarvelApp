package com.example.marvelapp1



import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class  SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        val handler = android.os.Handler()
        handler.postDelayed({

            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)
    }
}
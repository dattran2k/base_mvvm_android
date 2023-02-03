package vn.vtvnews.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import vn.vtvnews.databinding.ActivitySplashBinding
import vn.vtvnews.databinding.M00ActivityMainBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivitySplashBinding.inflate(layoutInflater).root)
        Handler(Looper.getMainLooper()).postDelayed({
            goToMainActivity()
        }, 1000)
    }
    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
package com.thefounder.save_bird

import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.media.MediaPlayer
import android.os.Bundle
import com.thefounder.save_bird.R
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.thefounder.save_bird.GameActivity

class MainActivity : AppCompatActivity() {
    private var bird: ImageView? = null
    private var enemy1: ImageView? = null
    private var enemy2: ImageView? = null
    private var enemy3: ImageView? = null
    private var coin: ImageView? = null
    private var volume: ImageView? = null
    private var buttonStart: Button? = null
    var animation: Animation? = null
    var mediaPlayer: MediaPlayer? = null
    var status = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bird = findViewById(R.id.bird)
        enemy1 = findViewById(R.id.enemy1)
        enemy2 = findViewById(R.id.enemy2)
        enemy3 = findViewById(R.id.enemy3)
        coin = findViewById(R.id.coin)
        volume = findViewById(R.id.volume)
        buttonStart = findViewById(R.id.button)
        animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        bird.setAnimation(animation)
        enemy1.setAnimation(animation)
        enemy2.setAnimation(animation)
        enemy3.setAnimation(animation)
        coin.setAnimation(animation)
        buttonStart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.music)
        mediaPlayer.start()
        volume!!.setOnClickListener {
            status = if (!status) {
                mediaPlayer.setVolume(0f, 0f)
                volume!!.setImageResource(R.drawable.ic_baseline_volume_off_24)
                true
            } else {
                mediaPlayer.setVolume(1f, 1f)
                volume!!.setImageResource(R.drawable.ic_baseline_volume_up_24)
                false
            }
        }
    }
}
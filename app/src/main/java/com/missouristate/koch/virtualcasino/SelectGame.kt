package com.missouristate.koch.virtualcasino

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.VideoView

class SelectGame : AppCompatActivity() {

    private lateinit var video: VideoView
    private lateinit var slotsButton: ImageButton
    private lateinit var rouletteButton: ImageButton
    private lateinit var blackjackButton: ImageButton
    private lateinit var funds: TextView
    private lateinit var videoUri: Uri
    private var balance = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_select)

        val intent = intent
        balance = intent.getIntExtra("balance", balance)

        video = findViewById(R.id.background_video)
        slotsButton = findViewById(R.id.slots_button)
        rouletteButton = findViewById(R.id.roulette_button)
        blackjackButton = findViewById(R.id.blackjack_button)
        funds = findViewById(R.id.funds_tv)

        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lights)
        video.setVideoURI(videoUri)
        video.start()
        video.setOnCompletionListener {
            video.start()
        }

        funds.text = ("Balance: $balance")

    }

    fun switchToSlots(view: View) {
        val getSlotsScreenIntent = Intent(this@SelectGame, Slots::class.java);
        getSlotsScreenIntent.putExtra("balance", balance)
        startActivity(getSlotsScreenIntent)
    }

    fun switchToRoulette(view: View) {
        val getSlotsScreenIntent = Intent(this@SelectGame, Roulette::class.java);
        getSlotsScreenIntent.putExtra("balance", balance)
        startActivity(getSlotsScreenIntent)
    }

    fun switchToBlackjack(view: View) {
        val getBlackjackScreenIntent = Intent(this@SelectGame, Blackjack::class.java);
        getBlackjackScreenIntent.putExtra("balance", balance)
        startActivity(getBlackjackScreenIntent)
    }

    //override fun onConfigurationChanged(newConfig: Configuration) {
    //    super.onConfigurationChanged(newConfig)
    //    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
    //        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lights_horizontal)
    //        video.setVideoURI(videoUri)
    //        video.start()
    //        video.setOnCompletionListener {
    //            video.start()
    //        }
    //    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
    //        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lights_vertical)
    //        video.setVideoURI(videoUri)
    //        video.start()
    //        video.setOnCompletionListener {
    //            video.start()
    //        }
    //    }
    //}
}
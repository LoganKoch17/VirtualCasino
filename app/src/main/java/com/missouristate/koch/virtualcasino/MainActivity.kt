package com.missouristate.koch.virtualcasino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var ttp_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun switchScreen(view: View) {
        ttp_button = findViewById(R.id.ttp_button)
        var getGameSelectScreenIntent = Intent(this, SelectGame::class.java);
        startActivity(getGameSelectScreenIntent)
    }
}
package com.example.broadcastreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("TAG", "Second activity started")
        val replyIntent = Intent("com.example.broadcastreceiver.intent.action.sms")
        replyIntent.putExtra("SMS", "test sms text")  // поставили строку в intent
        startActivity(replyIntent)  // Вызывается MainActivity, onCreate
    }
}
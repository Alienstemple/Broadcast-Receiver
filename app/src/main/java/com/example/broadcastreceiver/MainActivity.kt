package com.example.broadcastreceiver

import android.Manifest.permission.RECEIVE_SMS
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony

class MainActivity : AppCompatActivity() {
    companion object {
        val MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1
    }

    // Создали экземпляр receiver
    private val smsReceiver = MySmsReceiver()

    // Создали intent filter
    private val filter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(RECEIVE_SMS),
                MY_PERMISSIONS_REQUEST_RECEIVE_SMS)
        } else {
            // Если разрешения есть, регистрируем receiver
//            this.registerReceiver(smsReceiver, filter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Разрегистрируем
//        this.unregisterReceiver(smsReceiver)
    }
}

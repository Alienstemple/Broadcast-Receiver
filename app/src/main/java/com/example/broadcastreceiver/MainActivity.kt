package com.example.broadcastreceiver

import android.Manifest.permission.RECEIVE_SMS
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var smsReceiver: MySmsReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "We are in onCreate MainActivity")

        if (checkSelfPermission(RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(RECEIVE_SMS),
                MY_PERMISSIONS_REQUEST_RECEIVE_SMS)
        } else {
            Log.d("TAG", "We already have permissions")
            registerSmsReceiver()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_RECEIVE_SMS) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ) {
                Log.d("TAG", "Permission requested, before rgister rcv")
                registerSmsReceiver()
            } else {
                Toast.makeText(this, "No permission!", Toast.LENGTH_LONG).show()
            }
            return
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val smsTv = findViewById<TextView>(R.id.sms_tv)
        val smsText = intent?.getStringExtra("SMS").toString()
        Log.d("TAG", "onNewIntent called, extra string = $smsText")
        smsTv.text = smsText
    }

    private fun registerSmsReceiver() {
        Log.d("TAG", "In register sms rcv")
        // Создали экземпляр receiver
        smsReceiver = MySmsReceiver()

        // Создали intent filter
        val filter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)

        // Если разрешения есть, регистрируем receiver
        this.registerReceiver(smsReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Разрегистрируем
        this.unregisterReceiver(smsReceiver)
    }

    companion object {
        val MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1
    }
}

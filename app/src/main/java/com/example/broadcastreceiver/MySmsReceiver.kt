package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class MySmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val intentAction = intent.action
        val extras = intent.extras

        if (intentAction != null) {
            when (intentAction) {
                Telephony.Sms.Intents.SMS_RECEIVED_ACTION -> {
//                    val message: String? = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE)
                    Toast.makeText(context, "Sms received", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
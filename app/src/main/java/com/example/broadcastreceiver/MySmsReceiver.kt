package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class MySmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val intentAction = intent.action

        if (intentAction != null) {
            if (intentAction == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
                val messages: Array<SmsMessage> =
                    Telephony.Sms.Intents.getMessagesFromIntent(intent)

                Toast.makeText(context,
                    "Sms received: ${messages[0].messageBody}",
                    Toast.LENGTH_LONG).show()

                Log.d("TAG", "Sms received, before starting MainActivity")
                val replyIntent = Intent("com.example.broadcastreceiver.intent.action.sms")
                replyIntent.putExtra("SMS",
                    "Sms: ${messages[0].messageBody}")  // поставили строку в intent
                context.startActivity(replyIntent)  // Вызывается MainActivity


            }
        }
    }
}
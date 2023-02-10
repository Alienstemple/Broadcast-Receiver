package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class MySmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val intentAction = intent.action

        if (intentAction != null) {
            when (intentAction) {
                Telephony.Sms.Intents.SMS_RECEIVED_ACTION -> {
                    val messages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)

                    Toast.makeText(context, "Sms received: ${messages[0].messageBody}", Toast.LENGTH_LONG).show()

//                    val replyIntent: Intent = Intent(MainActivity::class.java).putExtra("SMS", "${messages[0].messageBody}")
//                    context.startActivity()
                }

            }
        }
    }
}
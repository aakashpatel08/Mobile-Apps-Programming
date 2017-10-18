package edu.uco.apatel19.p7aakashp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS data bound to intent
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages;
        String senderNumber = "";
        String body = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");

            messages = new SmsMessage[pdus.length];
            // For every SMS message received
            for (int i = 0; i < messages.length; i++) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                senderNumber = messages[i].getOriginatingAddress();
                body = messages[i].getMessageBody().toString();
            }
        }

        if (senderNumber.equals("11")) {
            if (body.equals("phone")) {
                Intent myIntent = new Intent(context, PhoneMapActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            } else if (body.equals("web")) {
                Intent myIntent = new Intent(context, WebMapActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            } else {
                Toast.makeText(context, "Invalid Keywords (phone or web): " + body, Toast.LENGTH_SHORT).show();
            }
        } else if (senderNumber.equals("22")) {
            Intent myIntent = new Intent(context, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            myIntent.putExtra("CITYNAME", body.toString());
            context.startActivity(myIntent);
        } else {
            Toast.makeText(context, "Invalid phone number: " + senderNumber, Toast.LENGTH_SHORT).show();
        }

    }
}

package com.andozia.agenda.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AndoReciver extends BroadcastReceiver {

    public static final String RECEIVER_EXTRA = "andoReceiver";
    public static final String RECEIVER_NAME = "com.andozia.agenda.receiver.AndoReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String origem = intent.getStringExtra(RECEIVER_EXTRA);

        Toast.makeText(context, String.format("Broadcast disparado por: %S", origem), Toast.LENGTH_LONG).show();

    }

}

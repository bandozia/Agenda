package com.andozia.agenda.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andozia.agenda.R;
import com.andozia.agenda.reciver.AndoReciver;


public class BroadcastActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_activity);
    }

    public void disparaBroadcast(View view){
        Intent i = new Intent(AndoReciver.RECEIVER_NAME);
        i.putExtra(AndoReciver.RECEIVER_EXTRA, "BroadcastActivity");

        sendBroadcast(i);
    }

}

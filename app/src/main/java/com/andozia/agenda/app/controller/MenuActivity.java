package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.andozia.agenda.R;


public class MenuActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        ((Button) findViewById(R.id.btMenuContato)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuDownload)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuWebservice)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuPhoto)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuBroadcast)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuSensor)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuBlueTooth)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuSms)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMenuMapa)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()){

            case R.id.btMenuContato:
                intent = new Intent(this, MenuContatoActivity.class);
                break;

            case R.id.btMenuDownload:
                intent = new Intent(this, DownloadImageActivity.class);
                break;

            case R.id.btMenuWebservice:
                intent = new Intent(this, WebServiceActivity.class);
                break;

            case R.id.btMenuPhoto:
                intent = new Intent(this, PhotoActivity.class);
                break;

            case R.id.btMenuBroadcast:
                intent = new Intent(this, BroadcastActivity.class);
                break;

            case R.id.btMenuSensor:
                intent = new Intent(this, SensorActivity.class);
                break;

            case R.id.btMenuBlueTooth:
                intent = new Intent(this, MenuBluetoohActivity.class);
                break;

            case R.id.btMenuSms:
                intent = new Intent(this, MenuSmsActivity.class);
                break;

            case R.id.btMenuMapa:
                intent = new Intent(this, MapaActivity.class);
                break;
        }

        if (intent != null){
            startActivity(intent);
        }

    }
}

package com.andozia.agenda.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andozia.agenda.R;


public class MenuBluetoohActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bluetooth_layout);
    }

    public void abreIntro(View v){
        Intent intent = new Intent(this, BluetoothIntroActivity.class);
        startActivity(intent);
    }

    public void abreChat(View v){

    }

}

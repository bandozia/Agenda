package com.andozia.agenda.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andozia.agenda.R;


public class SensorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sensor_layout);
    }

    void ClickList(View view){
        Intent intent = new Intent(this, ListaSersorActivity.class);
        startActivity(intent);
    }

    void clickAcelerometro(View view){
        Intent intent = new Intent(this, AcelerometroActivity.class);
        startActivity(intent);
    }

    void clickLuzProx(View view){
        Intent intent = new Intent(this, LuzProximidadeActivity.class);
        startActivity(intent);
    }

}

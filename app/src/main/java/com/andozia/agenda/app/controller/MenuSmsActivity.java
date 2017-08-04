package com.andozia.agenda.app.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.andozia.agenda.R;

public class MenuSmsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_sms_layout);

        ((Button) findViewById(R.id.btSmsEnviar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEnviar = new Intent(MenuSmsActivity.this, EnviarSmsActivity.class);
                startActivity(intentEnviar);
            }
        });

        ((Button) findViewById(R.id.btSmsReceber)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReceber = new Intent(MenuSmsActivity.this, ReceberSmsActivity.class);
                startActivity(intentReceber);
            }
        });
    }
}

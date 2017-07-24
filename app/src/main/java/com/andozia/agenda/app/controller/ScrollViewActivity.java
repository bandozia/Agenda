package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andozia.agenda.R;

/**
 * Created by no3603 on 14/07/2017.
 */

public class ScrollViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scroll_view);


        Button btTeste = (Button) findViewById(R.id.btSome);

        btTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Teste de botao",Toast.LENGTH_LONG).show();
            }
        });

    }
}

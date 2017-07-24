package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.andozia.agenda.R;

public class LinearLayoutActivity extends Activity implements View.OnClickListener {

    private Button btSome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        btSome = (Button)  findViewById(R.id.btSome);

        btSome.setOnClickListener(this);

        ((Button) findViewById(R.id.btAparece)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btSome){
            v.setVisibility(View.GONE);
        }else{
            btSome.setVisibility(View.VISIBLE);
        }
    }

}

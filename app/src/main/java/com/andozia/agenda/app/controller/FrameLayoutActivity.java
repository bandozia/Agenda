package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.andozia.agenda.R;


public class FrameLayoutActivity extends Activity {

    TextView testeTx;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.frame_layout);

        Log.i("ANDOZIA","frame layout activity ->[onCreate()]");

        testeTx = (TextView) findViewById(R.id.testeTx);
        handler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}

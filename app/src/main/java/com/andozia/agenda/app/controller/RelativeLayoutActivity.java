package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.andozia.agenda.R;



public class RelativeLayoutActivity extends Activity {

    Handler h;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        h = new Handler();

        setContentView(R.layout.relative_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}

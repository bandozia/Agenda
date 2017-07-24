package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andozia.agenda.R;

/**
 * Created by no3603 on 13/07/2017.
 */

public class GridLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.grid_layout);
    }
}

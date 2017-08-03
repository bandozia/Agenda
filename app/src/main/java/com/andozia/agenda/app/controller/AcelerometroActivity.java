package com.andozia.agenda.app.controller;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.andozia.agenda.R;

public class AcelerometroActivity extends BaseActivity implements SensorEventListener {

    private TextView txValX, txValY, txValZ;
    private SensorManager mSensorManager;
    private Sensor mAcelerometro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acelerometro_layout);

        txValX = (TextView) findViewById(R.id.acelValX);
        txValY = (TextView) findViewById(R.id.acelValY);
        txValZ = (TextView) findViewById(R.id.acelValZ);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcelerometro = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAcelerometro, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        txValX.setText(String.valueOf(x));
        txValY.setText(String.valueOf(y));
        txValZ.setText(String.valueOf(z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

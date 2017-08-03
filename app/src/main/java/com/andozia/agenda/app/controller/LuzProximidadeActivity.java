package com.andozia.agenda.app.controller;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andozia.agenda.R;

public class LuzProximidadeActivity extends BaseActivity {

    private ImageView imageView;
    private LinearLayout rootLayout;

    private SensorManager mSensorManager;
    private Sensor mSensorLuz;
    private Sensor mSensorProx;

    private ProxSensorListener proxSensorListener;
    private LuzSensorListener luzSensorListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luz_proximidade_layout);

        imageView = (ImageView) findViewById(R.id.luz_imvMic);
        rootLayout = (LinearLayout) findViewById(R.id.luz_RootLayout);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorLuz = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProx = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        luzSensorListener = new LuzSensorListener();
        proxSensorListener = new ProxSensorListener();

        mSensorManager.registerListener(luzSensorListener, mSensorLuz, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(proxSensorListener, mSensorProx, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(luzSensorListener);
        mSensorManager.unregisterListener(proxSensorListener);
    }

    private class LuzSensorListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float v = event.values[0];

            if (v < 10){
                rootLayout.setBackgroundColor(Color.BLACK);
            }else{
                rootLayout.setBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }


    private class ProxSensorListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float v = event.values[0];

            if (v >= 10){
                imageView.setImageResource(R.mipmap.microfone_off);
            }else{
                imageView.setImageResource(R.mipmap.microfone_on);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

}

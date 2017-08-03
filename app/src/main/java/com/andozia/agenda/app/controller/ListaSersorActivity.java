package com.andozia.agenda.app.controller;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andozia.agenda.R;

import java.util.List;

public class ListaSersorActivity extends BaseActivity {

    ListView listaSensores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_sensor_layout);

        listaSensores = (ListView) findViewById(R.id.listaSensores);
        String[] sensores = getSensores();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensores);
        listaSensores.setAdapter(adapter);

    }

    private String[] getSensores(){

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = manager.getSensorList(Sensor.TYPE_ALL);
        String[] sensoresArray = new String[sensorList.size()];

        for (int i = 0; i < sensorList.size(); i++){
            Sensor sensor = sensorList.get(i);

            sensoresArray[i] = String.format("Nome: %s\nTipo:%s", sensor.getName(), sensor.getStringType());
        }

        return sensoresArray;
    }
}

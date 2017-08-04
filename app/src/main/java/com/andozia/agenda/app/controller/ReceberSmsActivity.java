package com.andozia.agenda.app.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.andozia.agenda.R;

public class ReceberSmsActivity extends AppCompatActivity {

    TextView smsTextView;

    private static final int PERMISSION_REQUEST_READ_SMS = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receber_sms_layout);

        smsTextView = (TextView) findViewById(R.id.receberSmsTxView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
                        PERMISSION_REQUEST_READ_SMS);
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case PERMISSION_REQUEST_READ_SMS:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permitui
                }else{
                    //não permitui
                }

                break;

        }
    }



}

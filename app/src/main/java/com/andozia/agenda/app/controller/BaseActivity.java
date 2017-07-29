package com.andozia.agenda.app.controller;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by no3603 on 20/07/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected static final int MY_PERMISSSION_REQUEST_CAMERA = 1;
    protected static final int MY_PERMISSSION_REQUEST_STORAGE = 2;
    protected static final int MY_PERMISSSION_REQUEST_RECORD_AUDIO = 3;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSSION_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{

                }
                break;
            case MY_PERMISSSION_REQUEST_STORAGE:
                return;
        }

    }

    protected void CheckPermission(int myPermissionRequest, String permission){

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {permission},
                    myPermissionRequest);
        }
    }

}

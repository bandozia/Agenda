package com.andozia.agenda.app.controller;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.andozia.agenda.R;


public class PhotoActivity extends BaseActivity {

    private static final int REQUEST_IMAGE_CAPTURE_THUMB = 1;
    private static final int REEQUEST_IMAGE_CAPTURE_FULL = 2;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);

        imageView = (ImageView) findViewById(R.id.ph_imageView);

        CheckPermission(MY_PERMISSSION_REQUEST_CAMERA, Manifest.permission.CAMERA);
        CheckPermission(MY_PERMISSSION_REQUEST_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }
}

package com.andozia.agenda.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.andozia.agenda.domain.Cep;
import com.andozia.agenda.utils.Utils;


public class CepService extends IntentService {

    private static final String TAG = "CepService";
    public static final String CEP_EXTRA = "cep_extra";

    public CepService() {
        super("cep service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "handleIntent");
        String cepString = intent.getStringExtra(CEP_EXTRA);

        Utils utils = new Utils();
        Cep cep = utils.getCep(String.format("http://api.postmon.com.br/v1/cep/%s", cepString));

        stopSelf();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

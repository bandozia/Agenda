package com.andozia.agenda.tasks;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;
import java.util.Locale;

/**
 * Created by no3603 on 04/08/2017.
 */

public class BuscarLocalTask extends AsyncTaskLoader<List<Address>> {

    Context context;
    String local;
    List<Address> enderecosEncotrados;

    public BuscarLocalTask(Context activity, String local){
        super(activity);

        this.context = activity;
        this.local = local;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (enderecosEncotrados == null){
            forceLoad();
        }else{
            deliverResult(enderecosEncotrados);
        }
    }

    @Override
    public List<Address> loadInBackground() {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try{
            enderecosEncotrados = geocoder.getFromLocationName(local, 20);
        }catch (Exception e){
            e.printStackTrace();
        }

        return enderecosEncotrados;
    }
}

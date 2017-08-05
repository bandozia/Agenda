package com.andozia.agenda.app.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class MessageDialog extends DialogFragment {

    public static final int REQUEST_ERRO_PLAY_SERVICE = 1;
    private int codigoErro;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return GooglePlayServicesUtil.getErrorDialog(codigoErro, getActivity(), REQUEST_ERRO_PLAY_SERVICE);
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
    }
}

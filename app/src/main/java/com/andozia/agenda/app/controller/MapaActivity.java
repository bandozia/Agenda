package com.andozia.agenda.app.controller;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.andozia.agenda.R;
import com.andozia.agenda.tasks.BuscarLocalTask;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MapaActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;
    private LatLng origem;
    private GoogleApiClient apiClient;

    private static final int LOADER_ENDERECO = 1;
    private static final String EXTRA_ORIG = "orig";
    private static final String EXTRA_DEST = "dest";

    EditText edtLocal;
    ImageButton btnBuscar;
    LatLng destino;

    LoaderManager loaderManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_layout);

        loaderManager = getSupportLoaderManager();

        edtLocal = (EditText)findViewById(R.id.editTextmapaLocal);
        btnBuscar = (ImageButton) findViewById(R.id.btBuscarMapa);

        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btnBuscar.setEnabled(false);
                //buscarEndereco();
            }
        });

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaFragment);

        googleMap = fragment.getMap();
        origem = new LatLng(-23.56176,-46.655981);

        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        montaMapa();
    }

    @Override
    protected void onStart() {
        super.onStart();

        apiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {

        if (apiClient != null && apiClient.isConnected()){
            apiClient.disconnect();
        }
        super.onStop();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MessageDialog.REQUEST_ERRO_PLAY_SERVICE && requestCode == RESULT_OK){
            apiClient.connect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        obterUltimaLocalizacao();
    }

    @Override
    public void onConnectionSuspended(int i) {
        apiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()){
            try{
                connectionResult.startResolutionForResult(this, MessageDialog.REQUEST_ERRO_PLAY_SERVICE);
            }catch (IntentSender.SendIntentException e){
                e.printStackTrace();
            }
        }else{
           exibirErrorMsg(this, connectionResult.getErrorCode());
        }
    }

    private void montaMapa(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

 /*   private void atualizarMapa(){

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.sr_madruga);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(origem)
                .zoom(17)
                .bearing(90)
                .tilt(45)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origem, 17.f));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(origem).title("Aqui").snippet("Blabla").icon(icon);

        googleMap.addMarker(markerOptions);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }*/

    private void atualizarMapa(){

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origem, 17.f));
        googleMap.clear();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(origem).title("Aqui");

        googleMap.addMarker(markerOptions);
    }

    private void obterUltimaLocalizacao(){
        Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);

        if (location != null){
            origem = new LatLng(location.getLatitude(), location.getLongitude());
            atualizarMapa();
        }
    }

    private void exibirErrorMsg(FragmentActivity fragmentActivity, final int codigoErro){
        final String TAG = "DIALOG_ERRO_PLAY_SERVICE";

        if (getSupportFragmentManager().findFragmentByTag(TAG) == null){
            MessageDialog messageDialog = new MessageDialog();
            messageDialog.setCodigoErro(codigoErro);
            messageDialog.show(fragmentActivity.getSupportFragmentManager(), TAG);
        }
    }

    /*private void buscarEndereco(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(edtLocal.getWindowToken(),0);
        loaderManager.restartLoader(LOADER_ENDERECO,null, buscarLocalCallbacks);
    }

    LoaderManager.LoaderCallbacks<List<Address>> buscarLocalCallbacks = new LoaderManager.LoaderCallbacks<List<Address>>() {
        @Override
        public Loader<List<Address>> onCreateLoader(int id, Bundle args) {
            return new BuscarLocalTask(MapaActivity.this, edtLocal.getText().toString());
        }

        @Override
        public void onLoadFinished(List<Address> loader, List<Address> adress) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    //exibirListaEnderecos();
                }
            });
        }

        @Override
        public void onLoaderReset(Loader loader) {

        }
    };*/

}

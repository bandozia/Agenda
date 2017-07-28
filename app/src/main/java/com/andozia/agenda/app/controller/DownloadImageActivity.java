package com.andozia.agenda.app.controller;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.andozia.agenda.R;
import com.andozia.agenda.utils.Auxiliar;

import java.io.IOException;


public class DownloadImageActivity extends BaseActivity {

    private ImageView imageView;
    private Button downloadBt;
    private EditText enderecoEdtx;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_image);

        imageView = (ImageView) findViewById(R.id.download_imageView);
        downloadBt = (Button) findViewById(R.id.download_btBaixar);
        enderecoEdtx = (EditText) findViewById(R.id.download_editTex);

        downloadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarAsyncTask(enderecoEdtx.getText().toString());
            }
        });
    }

    private void chamarAsyncTask(String url){
        TarefaDownload tarefaDownload = new TarefaDownload();
        tarefaDownload.execute(url);
    }

    private class TarefaDownload extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(DownloadImageActivity.this, "Por favor aguarde...", "baixando a imagem...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imageBitmap = null;
            try{
                imageBitmap = Auxiliar.baixarImagem(params[0]);
            }catch (IOException ex){
                Log.e("AsyncTask download", ex.getMessage());
            }
            return imageBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }else{
                Log.e("AsyncTask download", "imagem nula");
            }
            progressDialog.dismiss();

        }
    }

}

package com.andozia.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andozia.agenda.R;
import com.andozia.agenda.utils.Auxiliar;
import com.andozia.contatolib.Contato;
import com.andozia.contatolib.ContatoPF;

import java.io.IOException;
import java.security.KeyPair;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContatoAdapter extends BaseAdapter {

    private static final String TAG = "ContatoAdapter";

    private Context context;
    private List<ContatoPF> contatos;

    private List<AbstractMap.SimpleEntry<ImageView, String>> imagesList;

    public ContatoAdapter(Context context, List<ContatoPF> contatos) {
        this.context = context;
        this.contatos = contatos;

        imagesList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = this.contatos.get(position);

        View linha = LayoutInflater.from(this.context).inflate(R.layout.contato_list_cell, parent, false);

        ImageView imageView = (ImageView) linha.findViewById(R.id.imgContato);
        TextView textViewNome = (TextView) linha.findViewById(R.id.contato_nome);
        TextView textViewEmail = (TextView) linha.findViewById(R.id.contato_email);

        textViewNome.setText(contato.getNome());
        textViewEmail.setText(contato.getEmail());

        imagesList.add(new AbstractMap.SimpleEntry<ImageView, String>(imageView, contato.getAvatar()));

        if (position == getCount() -1){

        }

        return linha;
    }


    //TODO: isso esta horrivel! repensar
    private class LoadImageAsync extends AsyncTask<Void,Bitmap,Void> {

        @Override
        protected void onProgressUpdate(Bitmap... values) {

        }

        @Override
        protected Void doInBackground(Void... params) {
            for (AbstractMap.SimpleEntry<ImageView, String> imageMap : imagesList ){

                if(imageMap.getValue() != null){
                    try {
                        Bitmap bitmap = Auxiliar.baixarImagem( imageMap.getValue() );
                        publishProgress(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            return null;
        }
    }

}

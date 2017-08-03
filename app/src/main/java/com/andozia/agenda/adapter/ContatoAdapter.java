package com.andozia.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;


public class ContatoAdapter extends BaseAdapter {

    private static final String TAG = "ContatoAdapter";

    private Context context;
    private List<ContatoPF> contatos;

    private List<AbstractMap.SimpleEntry<ContatoPF,Bitmap>> bitMapsList;

    public ContatoAdapter(Context context, List<ContatoPF> contatos) {
        this.context = context;
        this.contatos = contatos;

        bitMapsList = new ArrayList<>();

        for (ContatoPF contato : contatos){
            bitMapsList.add(new AbstractMap.SimpleEntry<ContatoPF, Bitmap>(contato, null));
        }

        LoadImageAsync loadImageAsync = new LoadImageAsync();
        loadImageAsync.execute();
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

        return linha;
    }

    //TODO: vincular ao progresso
    private class LoadImageAsync extends AsyncTask<Void,Bitmap,Void> {

        @Override
        protected void onProgressUpdate(Bitmap... values) {

        }

        @Override
        protected Void doInBackground(Void... params) {
            for (ContatoPF contatoPF : contatos ){

                try {
                    Bitmap bitmap = Auxiliar.baixarImagem( contatoPF.getAvatar() );
                    publishProgress(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

}

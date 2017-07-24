package com.andozia.agenda.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andozia.agenda.R;
import com.andozia.contatolib.Contato;
import com.andozia.contatolib.ContatoPF;

import java.util.List;

/**
 * Created by no3603 on 18/07/2017.
 */

public class ContatoAdapter extends BaseAdapter {

    private Context context;
    private List<ContatoPF> contatos;

    public ContatoAdapter(Context context, List<ContatoPF> contatos) {
        this.context = context;
        this.contatos = contatos;
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


}

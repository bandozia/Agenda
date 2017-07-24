package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.andozia.agenda.R;
import com.andozia.agenda.adapter.AutocompleteAdapter;
import com.andozia.agenda.adapter.ContatoAdapter;
import com.andozia.agenda.domain.ContatoDomain;
import com.andozia.contatolib.Contato;
import com.andozia.contatolib.ContatoPF;

import java.util.ArrayList;
import java.util.List;


public class ListaContatosActivity extends Activity implements AdapterView.OnItemClickListener {


    private AutoCompleteTextView edtBusca;
    private ListView lstContatos;
    private ContatoAdapter contatoAdapter;

    private List<ContatoPF> contatoPFs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lista_contatos_layout);

        edtBusca = (AutoCompleteTextView) findViewById(R.id.edtBuscar);
        lstContatos = (ListView) findViewById(R.id.lstContatos);

        atualizaLista();

        AutocompleteAdapter autocompleteAdapter = new AutocompleteAdapter(this, R.layout.autocomplete_layout, buildContatos());
        edtBusca.setAdapter(autocompleteAdapter);
        edtBusca.setThreshold(1);
        edtBusca.setOnItemClickListener(this);


        lstContatos.setOnItemClickListener(this);

    }

    private void atualizaLista(){

        contatoPFs = buildContatos();
        contatoAdapter = new ContatoAdapter(this,contatoPFs);

        lstContatos.setAdapter(contatoAdapter);
    }

    private List<ContatoPF> buildContatos(){
        ContatoDomain domain = new ContatoDomain(this);
        List<ContatoPF> contatos = domain.listaConatos();
        return contatos;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ContatoPF contato = (ContatoPF) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(this, ContatoActivity.class);
        intent.putExtra("contato", contato);
        startActivity(intent);

    }

}

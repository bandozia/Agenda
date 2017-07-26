package com.andozia.agenda.app.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.andozia.agenda.R;
import com.andozia.agenda.domain.Pessoa;
import com.andozia.agenda.utils.Utils;

public class WebServiceActivity extends BaseActivity {

    private TextView nomeTx;
    private TextView sobrenomeTx;
    private TextView emailTx;
    private TextView enderecoTx;
    private TextView cidadeTx;
    private TextView estadoTx;
    private TextView usernameTx;
    private TextView nascimentoTx;
    private TextView telefoneTx;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webservice_layoout);

        nomeTx = (TextView)findViewById(R.id.ws_nome);
        sobrenomeTx = (TextView)findViewById(R.id.ws_sobrenome);
        emailTx = (TextView)findViewById(R.id.ws_email);
        enderecoTx = (TextView)findViewById(R.id.ws_endereco);
        cidadeTx = (TextView)findViewById(R.id.ws_cidade);
        estadoTx = (TextView)findViewById(R.id.ws_estado);
        nascimentoTx = (TextView)findViewById(R.id.ws_nascimento);
        telefoneTx = (TextView)findViewById(R.id.ws_telefone);
        usernameTx = (TextView)findViewById(R.id.ws_telefone);

        GetJson getJson = new GetJson();
        getJson.execute();
    }

    private void popularResult(Pessoa pessoa){
        nomeTx.setText(pessoa.getNome());
        sobrenomeTx.setText(pessoa.getSobrenome());
        emailTx.setText(pessoa.getEmail());
        enderecoTx.setText(pessoa.getEndereco());
        cidadeTx.setText(pessoa.getCidade());
        estadoTx.setText(pessoa.getEstado());
        nascimentoTx.setText(pessoa.getNascimento());
        telefoneTx.setText(pessoa.getTelefone());
        usernameTx.setText(pessoa.getUsername());
    }

    private ProgressDialog progressDialog;

    private class GetJson extends AsyncTask<Void,Void,Pessoa>{

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(WebServiceActivity.this, "aguarde", "recuperando informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            Utils utils = new Utils();
            return utils.getInfo("https://randomuser.me/api");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa) {
            progressDialog.dismiss();
            popularResult(pessoa);
        }
    }

}

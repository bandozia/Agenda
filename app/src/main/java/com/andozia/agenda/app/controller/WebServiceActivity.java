package com.andozia.agenda.app.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andozia.agenda.R;
import com.andozia.agenda.domain.ContatoDomain;
import com.andozia.agenda.domain.Pessoa;
import com.andozia.agenda.utils.Utils;
import com.andozia.contatolib.ContatoPF;

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
    private ImageView fotoImv;

    private Pessoa pessoa;

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
        usernameTx = (TextView)findViewById(R.id.ws_username);
        fotoImv = (ImageView) findViewById(R.id.ws_ImageView);

        recarregarPessoa();

        ((Button) findViewById(R.id.ws_btSalvarContato)).setOnClickListener(new SalvarContato());
    }

    private void recarregarPessoa()
    {
        GetJson getJson = new GetJson();
        getJson.execute();

    }

    private void popularResult(Pessoa pessoa){
        this.pessoa = pessoa;

        nomeTx.setText(pessoa.getNome());
        sobrenomeTx.setText(pessoa.getSobrenome());
        emailTx.setText(pessoa.getEmail());
        enderecoTx.setText(pessoa.getEndereco());
        cidadeTx.setText(pessoa.getCidade());
        estadoTx.setText(pessoa.getEstado());
        nascimentoTx.setText(pessoa.getNascimento());
        telefoneTx.setText(pessoa.getTelefone());
        usernameTx.setText(pessoa.getUsername());

        fotoImv.setImageBitmap(pessoa.getFoto());
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

    private class SalvarContato implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            ContatoDomain contatoDomain = new ContatoDomain(WebServiceActivity.this);
            ContatoPF contatoPF = new ContatoPF(pessoa.getNome(), pessoa.getEmail(), "");
            contatoPF.setSobrenome(pessoa.getSobrenome());
            contatoPF.setAvatar(pessoa.getCaminhoFoto());
            //TODO: popular o restante das infos
            long id = -1;
            if((id = contatoDomain.salvar(contatoPF)) > 0){
                Toast.makeText(WebServiceActivity.this, pessoa.getNome() + " salvo no banco: " + id, Toast.LENGTH_SHORT).show();
                recarregarPessoa();
            }else{
                Log.e("WebServiceActivity", "deu pau pra salvar a pessoa");
            }
        }
    }

}


//olhar isso: https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
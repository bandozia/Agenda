package com.andozia.agenda.app.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andozia.agenda.R;
import com.andozia.agenda.domain.ContatoDomain;
import com.andozia.agenda.services.CepService;
import com.andozia.contatolib.Contato;
import com.andozia.contatolib.ContatoPF;

import java.net.URL;


public class ContatoActivity extends Activity implements TextWatcher {

    private static final String SERVICE_KEY_CEP = "service_key_cep";

    private ImageView imageView;
    private EditText cpfEditText;
    private EditText nomeEdText,emailEdText,sobrenomeEdText;
    private Button salvarContatoBt;
    private Button deletarContatoBt;
    private EditText cepEditText;
    private EditText enderecoEditTex;

    private boolean isUpdating;
    private ContatoPF contatoInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato_layout);

        contatoInstance = (ContatoPF) getIntent().getSerializableExtra("contato");

        imageView = (ImageView) findViewById(R.id.imgUser);
        cpfEditText = (EditText) findViewById(R.id.edTxCpf);

        nomeEdText = (EditText) findViewById(R.id.edtxNome);
        emailEdText = (EditText) findViewById(R.id.edTxEmail);
        sobrenomeEdText = (EditText) findViewById(R.id.edTxSobrenome);
        cepEditText = (EditText) findViewById(R.id.edTxCep);
        enderecoEditTex = (EditText) findViewById(R.id.edTxEndereco);

        salvarContatoBt = (Button) findViewById(R.id.btCadastrarContato);
        deletarContatoBt = (Button) findViewById(R.id.btDeletarContato);
        ((Button) findViewById(R.id.fotinhaBt)).setOnClickListener(new TakeFotinhaActionClick());

        if (contatoInstance != null){
            salvarContatoBt.setText("atualizar");
            salvarContatoBt.setOnClickListener(new atualizarContatoListener());
            deletarContatoBt.setVisibility(View.VISIBLE);
            deletarContatoBt.setOnClickListener(new deletarContatoListener());
        }else{
            salvarContatoBt.setOnClickListener(new salvarContatoListener());
        }


        cepEditText.setOnFocusChangeListener(new onCepFocus());
        cpfEditText.addTextChangedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (contatoInstance != null){
            popularContatoSalvo();
        }
    }

    private void popularContatoSalvo(){
        nomeEdText.setText(contatoInstance.getNome());
        sobrenomeEdText.setText(contatoInstance.getSobrenome());
        emailEdText.setText(contatoInstance.getEmail());
        cpfEditText.setText(contatoInstance.getCpf());

        //TODO: colocar imagem do usuario
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bmp);

        }else{
            Toast.makeText(getApplicationContext(), "DEU MERDA", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (isUpdating){
            isUpdating = false;
            return;
        }

        boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;
        String str = s.toString().replaceAll("[.]","").replaceAll("[-]","");

        if (count > before){
            if (str.length() > 8) {
                str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6, 9) + '-' + str.substring(9);
            }else if(str.length() > 6){
                str = str.substring(0,3) + '.' + str.substring(3,6) + '.' + str.substring(6);
            }else if(str.length() > 3){
                str = str.substring(0,3) + '.' + str.substring(3);
            }

            isUpdating = true;

            cpfEditText.setText(str);
            cpfEditText.setSelection(cpfEditText.getText().length());
        }else{
            isUpdating = true;
            cpfEditText.setText(str);
            cpfEditText.setSelection(cpfEditText.getText().length());
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private class TakeFotinhaActionClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 666);

        }
    }

    private void salvarContato(ContatoPF contato){
        ContatoDomain domain = new ContatoDomain(this);
        long id = domain.salvar(contato);

        if (id > 0){
            Log.i("salvar contato", "id:" + id);
            Intent intent = new Intent(this, ListaContatosActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "deu alguma merda", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizarContato(ContatoPF contato){
        ContatoDomain domain = new ContatoDomain(this);

        if(domain.atualizar(contato)){
            Intent intent = new Intent(this, ListaContatosActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "DEU PAU!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletarContato(ContatoPF contato){
        ContatoDomain domain = new ContatoDomain(this);
        domain.excluir(contato);
        Intent intent = new Intent(this, ListaContatosActivity.class);
        startActivity(intent);
    }

    private class salvarContatoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ContatoPF contatoPF = new ContatoPF(
                    nomeEdText.getText().toString(),
                    emailEdText.getText().toString(),
                    cpfEditText.getText().toString()
            );

            contatoPF.setSobrenome(sobrenomeEdText.getText().toString());
            //TODO: salvar avatar

            salvarContato(contatoPF);
        }
    }

    private class atualizarContatoListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            contatoInstance.setNome(nomeEdText.getText().toString());
            contatoInstance.setSobrenome(sobrenomeEdText.getText().toString());
            contatoInstance.setEmail(emailEdText.getText().toString());
            contatoInstance.setCpf(cpfEditText.getText().toString());
            //TODO: atualizar avatar

            atualizarContato(contatoInstance);
        }
    }

    private class deletarContatoListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            deletarContato(contatoInstance);
        }
    }

    private class onCepFocus implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (cepEditText.getText() != null && !cepEditText.getText().toString().equals("")){
                    Intent intent = new Intent(ContatoActivity.this, CepService.class);
                    intent.putExtra(CepService.CEP_EXTRA, cepEditText.getText().toString());
                    startService(intent);
                }
            }
        }
    }
}

/***Olhar:******

 - ListFragment
 - Implementação de interface na List Fragment
 ContatoDetalheFragmentActivity

 ContatoListFragment
 ContatoFragmentActivity

 ***********/
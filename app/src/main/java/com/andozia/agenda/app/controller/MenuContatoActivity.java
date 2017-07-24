package com.andozia.agenda.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.andozia.agenda.R;
import com.andozia.agenda.dao.CriaBanco;


public class MenuContatoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_contato_layout);

        CriaBanco criaBanco = new CriaBanco(this);
        criaBanco.getWritableDatabase();
        criaBanco.close();

        ((Button) findViewById(R.id.btListagem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuContatoActivity.this, ListaContatosActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btCadastro)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuContatoActivity.this, ContatoActivity.class);
                startActivity(intent);
            }
        });

    }
}

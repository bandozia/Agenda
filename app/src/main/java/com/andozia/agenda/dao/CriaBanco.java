package com.andozia.agenda.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andozia.agenda.dao.helper.BancoHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String DB_NAME = "contato_db.sqlite";
    private static final int VERSION = 1;

    public CriaBanco(Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("CriaBanco", "criando tabelas");

        try{
            db.execSQL(BancoHelper.sqlTabelaContato());
        }catch (Exception e){
            Log.e("CriaBanco", "deu pau");
        }

        Log.i("CriaBanco", "criacao de tabelas finalizada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*try{
            db.execSQL("ALTER TABLE contato ADD COLUMN avatar VARCHAR(100)");
            Log.i("CriaBanco", "banco updatado");
        }catch (Exception e){
            Log.e("CriaBanco","deu pau pra alterar a tabela:" + e.getMessage());
        }*/
    }


}

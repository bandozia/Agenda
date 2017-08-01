package com.andozia.agenda.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andozia.agenda.dao.CriaBanco;
import com.andozia.contatolib.ContatoPF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by no3603 on 20/07/2017.
 */

public class ContatoDomain {

    private Context context;

    public ContatoDomain(Context context) {
        this.context = context;
    }

    public List<ContatoPF> listaConatos(){
        List<ContatoPF> contaos = Collections.emptyList();

        Cursor cursor = null;
        String[] campos = {"_id","nome", "sobrenome", "email","cpf","avatar", "cep", "endereco"};

        CriaBanco banco = new CriaBanco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        try{
            cursor = db.query("contato",campos,null,null,null,null,null);

            if (cursor != null){
                cursor.moveToFirst();
                contaos = new ArrayList<>();

                do{
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                    String sobrenome = cursor.getString(cursor.getColumnIndexOrThrow("sobrenome"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                    String avatar = cursor.getString(cursor.getColumnIndexOrThrow("avatar"));
                    String cep = cursor.getString(cursor.getColumnIndexOrThrow("cep"));
                    String endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"));
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                    ContatoPF contato = new ContatoPF(nome,email,cpf);
                    contato.setSobrenome(sobrenome);
                    contato.setId(id);
                    contato.setAvatar(avatar);
                    contato.setEndereco(endereco);
                    contato.setCep(cep);

                    contaos.add(contato);
                }while(cursor.moveToNext());


            }
        }catch (Exception e){
            Log.e("listarContatos", "deu pau");
        }finally {
            if (cursor != null)
                cursor.close();

            db.close();
        }

        return contaos;
    }

    public boolean atualizar(ContatoPF contato){
        CriaBanco banco = new CriaBanco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("sobrenome", contato.getSobrenome());
        valores.put("cpf", contato.getCpf());
        valores.put("avatar", contato.getAvatar());
        valores.put("cep", contato.getCep());
        valores.put("endereco", contato.getEndereco());

        String where = "_id=" + contato.getId();

        Log.i("atualizar", "nome:" + contato.getNome());

        boolean sucesso = false;

        try{
            db.update("contato", valores, where,null);
            sucesso = true;
        }catch (Exception e){
            Log.e("ContatoDomain update", "deu pau: " + e.getMessage());
            sucesso = false;
        }finally {
            db.close();
        }

        return sucesso;
    }

    public void excluir(ContatoPF contato){
        CriaBanco banco = new CriaBanco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        String where = "_id=" + contato.getId();

        try{
            db.delete("contato", where, null);
        }catch (Exception e){
            Log.e("ContatoDomain update", "deu pau");
        }finally {
            db.close();
        }
    }

    public long salvar(ContatoPF contato){
        CriaBanco banco = new CriaBanco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        long resultado = -1;

        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("sobrenome", contato.getSobrenome());
        valores.put("cpf", contato.getCpf());
        valores.put("avatar", contato.getAvatar());
        valores.put("cep", contato.getCpf());
        valores.put("endereco", contato.getEndereco());

        try{
            resultado = db.insert("contato", null, valores);
        }catch (Exception e){
            Log.e("ContatoDomain", "deu pau");
        }finally {
            db.close();
        }

        return resultado;
    }

}

package com.andozia.agenda.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.andozia.agenda.domain.Pessoa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by no3603 on 25/07/2017.
 */

public class Utils {

    public Pessoa getInfo(String url){
        Pessoa pessoa;

        String json = NetworkUtils.getJsonFromAPI(url);
        pessoa = parseJson(json);

        return pessoa;
    }

    private Pessoa parseJson(String json){
        Pessoa pessoa = null;

        try{
            pessoa = new Pessoa();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date data;

            JSONObject mainObject = jsonArray.getJSONObject(0);
            pessoa.setNome(mainObject.getJSONObject("name").getString("first"));
            pessoa.setSobrenome(mainObject.getJSONObject("name").getString("last"));
            pessoa.setEndereco(mainObject.getJSONObject("location").getString("street"));
            pessoa.setCidade(mainObject.getJSONObject("location").getString("city"));
            pessoa.setEstado(mainObject.getJSONObject("location").getString("state"));
            pessoa.setEmail(mainObject.getString("email"));
            pessoa.setUsername(mainObject.getJSONObject("login").getString("username"));
            pessoa.setNascimento(mainObject.getString("dob"));
            pessoa.setTelefone(mainObject.getString("phone"));
            pessoa.setCaminhoFoto(mainObject.getJSONObject("picture").getString("large"));
            pessoa.setFoto(baixarImagem(mainObject.getJSONObject("picture").getString("large")));

        }catch (Exception e){
            Log.e("Utils", e.getMessage());
        }

        return pessoa;
    }

    private Bitmap baixarImagem(String urlString){
        Bitmap bitmap = null;
        URL url = null;
        InputStream inputStream = null;

        try{
            url = new URL(urlString);
            inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            Log.e("Utils", e.getMessage());
        }finally {
            if (inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }


        return bitmap;
    }

}

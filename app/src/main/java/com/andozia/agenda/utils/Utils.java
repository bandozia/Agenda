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

            JSONObject object = jsonArray.getJSONObject(0);

            pessoa.setEmail(object.getString("email"));
            pessoa.setUsername(object.getString("username"));
            pessoa.setSenha(object.getString("password"));
            pessoa.setTelefone(object.getString("phone"));

            pessoa.setNascimento(object.getString("dob"));

            JSONObject nome = object.getJSONObject("name");

            pessoa.setNome(object.getString("first"));
            pessoa.setSobrenome(object.getString("last"));

            JSONObject endereco = object.getJSONObject("location");
            pessoa.setEndereco(endereco.getString("street"));
            pessoa.setCidade(endereco.getString("city"));
            pessoa.setEstado(endereco.getString("state"));

            JSONObject foto = object.getJSONObject("picture");
            pessoa.setCaminhoFoto(foto.getString("large"));
            pessoa.setFoto(baixarImagem(foto.getString("large")));

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

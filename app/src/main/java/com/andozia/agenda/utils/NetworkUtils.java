package com.andozia.agenda.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by no3603 on 25/07/2017.
 */

public class NetworkUtils {

    public static String getJsonFromAPI(String url){
        String retorno = "";

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            URL endpoint = new URL(url);
            int returnCode;


            connection = (HttpURLConnection) endpoint.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            returnCode = connection.getResponseCode();

            if (returnCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            retorno = convertInputStreamToString(inputStream);

        }catch (MalformedURLException e){
            Log.e("NetworkUtils", e.getMessage());
        }catch (Exception e){
            Log.e("NetworkUtils", e.getMessage());
        }finally {
            if (inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                connection.disconnect();
            }
        }

        return retorno;
    }

    private static String convertInputStreamToString(InputStream stream){
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        try{
            String reader;

            while ((reader = bufferedReader.readLine()) != null ){
                stringBuffer.append(reader);
            }

        }catch (IOException e){
            Log.e("NetworkUtils", e.getMessage());
        }finally {
            try{
                bufferedReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return stringBuffer.toString();
    }


}

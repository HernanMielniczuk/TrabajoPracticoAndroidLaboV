package com.trabajopracticolabov.hernanmielniczuk.buffet.DAO;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hernan on 09/06/2017.
 */

public class Conexion {

    public static String getIPwithPort(){
        return "http://192.168.0.4:3000";
    }

    public byte[] getBytesDataByGet (String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        int response = urlConnection.getResponseCode();
        Log.d("HTTP", "Response code: " + response);
        if (response == 200) {
            InputStream is = urlConnection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            is.close();
            return baos.toByteArray();
        } else throw new IOException();
    }

    public boolean setBytesDataByPost(String u, JSONObject obj) throws IOException {

        URL url = new URL(u);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        OutputStream os = urlConnection.getOutputStream();
        byte[] outputBytes = obj.toString().getBytes("UTF-8");
        os.write(outputBytes);
        urlConnection.connect();
        int response = urlConnection.getResponseCode();
        if (response == 200) {
            InputStream is = urlConnection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while((length = is.read(buffer))!= -1) {
                baos.write(buffer, 0 , length);
            }
            return baos.toString().contains("Se inserto correctamente");
        } else throw new IOException();
    }
}
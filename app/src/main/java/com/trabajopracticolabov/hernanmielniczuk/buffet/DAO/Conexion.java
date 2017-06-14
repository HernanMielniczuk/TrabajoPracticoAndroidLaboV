package com.trabajopracticolabov.hernanmielniczuk.buffet.DAO;

import android.net.Uri;
import android.util.Log;

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

    public byte[] getBytesDataByPost(String u, Uri.Builder postParams) throws IOException {
        URL url = new URL(u);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.connect();
        urlConnection.setDoOutput(true);
        String query = postParams.build().getEncodedQuery();
        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        int response = urlConnection.getResponseCode();
        if(response == 200) {
            InputStream is = urlConnection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while((length = is.read(buffer)) != -1){
                baos.write(buffer, 0, length);
            }
            is.close();
            return baos.toByteArray();
        }
        throw new IOException();
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.DAO;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
}

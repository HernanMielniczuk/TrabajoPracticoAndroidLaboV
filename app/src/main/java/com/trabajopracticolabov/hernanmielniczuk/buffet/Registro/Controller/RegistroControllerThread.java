package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;

import java.io.IOException;

/**
 * Created by Hernan on 14/06/2017.
 */

public class RegistroControllerThread implements Runnable {

    private String url;
    private Uri.Builder params;
    private Handler handler;

    public RegistroControllerThread(String u, Uri.Builder p, Handler h){
        url = u;
        params = p;
        handler = h;
    }

    @Override
    public void run() {
        Message message = new Message();
        try{
            Conexion conexion = new Conexion();
            String respuesta = new String(conexion.getBytesDataByPost(url, params));
            message.arg1 = 2;
            message.obj = respuesta;
            handler.sendMessage(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

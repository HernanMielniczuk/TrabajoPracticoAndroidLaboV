package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller;

import android.os.Handler;
import android.os.Message;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;

import java.io.IOException;

/**
 * Created by Hernan on 09/06/2017.
 */

public class MenuControllerThread implements Runnable {

    private String url;
    private Handler handler;

    public MenuControllerThread(String u, Handler h){
        url = u;
        handler = h;
    }

    @Override
    public void run() {
        Message message = new Message();
        try {
            Conexion conexion = new Conexion();
            String respuesta = new String(conexion.getBytesDataByGet(url));
            //String respuesta = new String(conexion.getBytesDataByGet("http://192.168.0.8:3000/productos"));
            message.arg1 = 1;
            message.obj = respuesta;
            handler.sendMessage(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

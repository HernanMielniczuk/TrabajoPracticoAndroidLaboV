package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.os.Handler;
import android.os.Message;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;

import java.io.IOException;

/**
 * Created by Hernan on 15/06/2017.
 */

public class BuscarUsuarioThread implements Runnable {

    private String mail;
    private Handler handler;

    public BuscarUsuarioThread(String m, Handler h){
        mail = m;
        handler = h;
    }

    @Override
    public void run() {
        Conexion conexion = new Conexion();
        Message message = new Message();
        String respuesta = null;
        try {
            respuesta = new String(conexion.getBytesDataByGet(Conexion.getIPwithPort() + "/usuarios/" + mail));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.obj = respuesta != null ? respuesta : "[]";
        message.arg1 = 1;
        handler.sendMessage(message);
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.os.Handler;
import android.os.Message;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;

import java.io.IOException;

/**
 * Created by Hernan on 15/06/2017.
 */

public class ValidarUsuarioThread implements Runnable {

    private Handler handler;
    private String mail;
    private String password;

    public ValidarUsuarioThread(String m, String p, Handler h){
        handler = h;
        mail = m;
        password = p;
    }

    @Override
    public void run() {
        Conexion conexion = new Conexion();
        Message message = new Message();
        String respuesta = null;
        try {
            respuesta = new String(conexion.getBytesDataByGet(Conexion.getIPwithPort() + "/usuarios/" + mail + "/" + password));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.obj = respuesta != null ? respuesta : "[]";
        message.arg1 = 2;
        handler.sendMessage(message);

    }
}

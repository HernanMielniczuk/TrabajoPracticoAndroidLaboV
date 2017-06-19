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
        synchronized (this) {
            Conexion conexion = new Conexion();
            Message message = new Message();

            try {
                String respuesta = new String(conexion.getBytesDataByGet(Conexion.getIPwithPort() + "/usuarios/" + mail));
                if (!respuesta.isEmpty()) {
                    message.obj = respuesta;
                    message.arg1 = 1;
                    handler.sendMessage(message);
                } else {
                    throw new IOException("No se recibió contenido en la respuesta.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

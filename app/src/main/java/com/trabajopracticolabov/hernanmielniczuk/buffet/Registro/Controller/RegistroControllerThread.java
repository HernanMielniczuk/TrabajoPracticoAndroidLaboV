package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller;

import android.os.Handler;
import android.os.Message;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Hernan on 14/06/2017.
 */

public class RegistroControllerThread implements Runnable {

    private String url;
    private Usuario usuario;
    private Handler handler;

    public RegistroControllerThread(String u, Usuario user, Handler h){
        url = u;
        usuario = user;
        handler = h;
    }

    @Override
    public void run() {
        Message message = new Message();
        JSONObject json = new JSONObject();
        Conexion conexion = new Conexion();
        try{
            json.put("nombre", usuario.getNombre());
            json.put("apellido", usuario.getApellido());
            json.put("dni", usuario.getDNI());
            json.put("mail", usuario.getEmail());
            json.put("clave", usuario.getPassword());
            message.arg1 = 2;
            message.arg2 = conexion.setBytesDataByPost(url, json) ? 200 : 500;
            handler.sendMessage(message);
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
}

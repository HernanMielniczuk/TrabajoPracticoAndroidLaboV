package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity.PedidoActivity;

import org.json.JSONException;

import java.io.Serializable;
import java.util.List;

import Utilities.JSONParser.BuffetJSONParser;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuController implements Handler.Callback {

    private MenuActivity activity;
    private MenuView view;
    private AlternarTabMenuListener alternarTabMenuListener;
    private VerPedidoListener verPedidoListener;
    private LogoutListener logoutListener;

    public AlternarTabMenuListener getAlternarTabMenuListener(){ return alternarTabMenuListener; }
    public VerPedidoListener getVerPedidoListener(){ return verPedidoListener; }
    public LogoutListener getLogoutListener(){ return logoutListener; }

    public MenuController(MenuActivity a, MenuView v, AlternarTabMenuListener atmListener, VerPedidoListener vpListener, LogoutListener lListener){
        activity = a;
        view = v;
        alternarTabMenuListener = atmListener;
        verPedidoListener = vpListener;
        logoutListener = lListener;
        v.setListeners(this);
        pedirProductos();
    }

    private void pedirProductos() {
        Handler handler = new Handler(this);
        Thread t = new Thread(new MenuControllerThread(Conexion.getIPwithPort() + "/productos", handler), "ThreadPedirProductos");
        t.start();
    }

    public static void irAPedido(AppCompatActivity a){
        Intent intent = new Intent(a, PedidoActivity.class);
        a.startActivity(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
            BuffetJSONParser parser = new BuffetJSONParser();
            try {
                view.cargarListas(parser.parsearProductos((String) msg.obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import Utilities.JSONParser.BuffetJSONParser;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuController {

    private MenuActivity activity;
    private MenuView view;
    private AlternarTabMenuListener alternarTabMenuListener;
    private VerPedidoListener verPedidoListener;
    private LogoutListener logoutListener;
    //private Handler handler;

    public AlternarTabMenuListener getAlternarTabMenuListener(){ return alternarTabMenuListener; }
    public VerPedidoListener getVerPedidoListener(){ return verPedidoListener; }
    public LogoutListener getLogoutListener(){ return logoutListener; }

    public MenuController(MenuActivity a, MenuView v, AlternarTabMenuListener atmListener, VerPedidoListener vpListener, LogoutListener lListener){
        activity = a;
        view = v;
        alternarTabMenuListener = atmListener;
        verPedidoListener = vpListener;
        logoutListener = lListener;
        //handler = new Handler(this);
        //pedirProductos(handler);
    }

    /*private void pedirProductos(Handler h){
        Thread t = new Thread(new MenuControllerThread(h), "ThreadPedirProductos");
        t.run();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1){
            BuffetJSONParser parser = new BuffetJSONParser();
            try {
                view.cargarListas(parser.parsearProductos((String) msg.obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    } */
}

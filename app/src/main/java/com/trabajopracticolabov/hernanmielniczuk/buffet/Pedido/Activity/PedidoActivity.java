package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Controller.PedidoController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.View.PedidoView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import Utilities.ActionBarHelper;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        ActionBarHelper.invalidateActionBar(this);

        PedidoView view = new PedidoView(this);
        PedidoController c = new PedidoController(new LogoutListener(view));
    }
}
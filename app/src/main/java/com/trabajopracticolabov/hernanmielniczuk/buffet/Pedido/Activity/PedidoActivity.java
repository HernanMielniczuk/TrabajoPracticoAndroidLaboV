package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Controller.PedidoController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.View.PedidoView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utilities.ActionBarHelper;

public class PedidoActivity extends AppCompatActivity {

    private List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        ActionBarHelper.invalidateActionBar(this);

        productos = (List<Producto>)getIntent().getSerializableExtra("pedido");

        PedidoView view = new PedidoView(this, productos);
        PedidoController c = new PedidoController(new LogoutListener(view));

    }
}

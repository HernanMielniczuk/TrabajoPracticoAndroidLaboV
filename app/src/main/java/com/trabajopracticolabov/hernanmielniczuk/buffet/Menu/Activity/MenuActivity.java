package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import Utilities.ActionBarHelper;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBarHelper.invalidateActionBar(this);

        MenuView view = new MenuView(this);
        MenuController c = new MenuController(this, view, new AlternarTabMenuListener(view), new VerPedidoListener(view), new LogoutListener(view));
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 15/05/2017.
 */

public class VerPedidoListener implements View.OnClickListener {

    private IVerPedido pedido;

    public VerPedidoListener(IVerPedido v){
        pedido = v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVerPedido){
            pedido.verPedido();
        }
    }
}


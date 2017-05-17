package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Controller;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;

/**
 * Created by Hernan on 16/05/2017.
 */

public class PedidoController {

    private LogoutListener logoutListener;

    public LogoutListener getLogoutListener(){ return logoutListener; }

    public PedidoController(LogoutListener lListener) {
        logoutListener = lListener;
    }
}

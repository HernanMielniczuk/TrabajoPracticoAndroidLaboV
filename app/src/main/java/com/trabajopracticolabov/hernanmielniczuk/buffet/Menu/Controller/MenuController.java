package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuController {


    private AlternarTabMenuListener alternarTabMenuListener;
    private VerPedidoListener verPedidoListener;
    private LogoutListener logoutListener;

    public AlternarTabMenuListener getAlternarTabMenuListener(){ return alternarTabMenuListener; }
    public VerPedidoListener getVerPedidoListener(){ return verPedidoListener; }
    public LogoutListener getLogoutListener(){ return logoutListener; }

    public MenuController(AlternarTabMenuListener atmListener, VerPedidoListener vpListener, LogoutListener lListener){
        alternarTabMenuListener = atmListener;
        verPedidoListener = vpListener;
        logoutListener = lListener;
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;

/**
 * Created by Hernan on 15/05/2017.
 */

public interface IGestionProducto {
    void agregarProducto(Producto p);
    void quitarProducto(Producto p);
}

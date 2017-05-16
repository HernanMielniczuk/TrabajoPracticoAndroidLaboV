package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model;

import java.io.Serializable;

/**
 * Created by Hernan on 13/05/2017.
 */

@SuppressWarnings("serial")
public class Producto implements Serializable {
    private String nombre;
    private Double precio;
    private CategoriaProducto categoría;

    public String getNombre(){
        return nombre;
    }

    public Double getPrecio(){
        return precio;
    }

    public CategoriaProducto getCategoría(){
        return categoría;
    }

    public Producto(String n, Double p, CategoriaProducto c){
        nombre = n;
        precio = p;
        categoría = c;
    }
}
package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.CategoriaProducto;

/**
 * Created by Hernan on 13/05/2017.
 */

public class Producto {
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
package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model;

import java.io.Serializable;

/**
 * Created by Hernan on 13/05/2017.
 */

@SuppressWarnings("serial")
public class Producto implements Serializable {
    private String id;
    private String nombre;
    private Double precio;
    private CategoriaProducto categoría;
    private String urlImagen;
    private byte[] imagen;

    public String getId() { return id; }

    public String getNombre(){
        return nombre;
    }

    public Double getPrecio(){ return precio; }

    public CategoriaProducto getCategoría(){
        return categoría;
    }

    public String getUrlImagen() { return urlImagen; }

    public byte[] getImagen() { return imagen; }

    public void setId(String i){ id = i; }

    public void setNombre(String n){ nombre = n; }

    public void setPrecio(Double p){ precio = p; }

    public void setCategoría(CategoriaProducto c) { categoría = c; }

    public void setUrlImagen(String u) { urlImagen = u; }

    public void setImagen(byte[] img) {imagen = img; }

    public Producto(){}

    public Producto(String n, Double p, CategoriaProducto c){
        nombre = n;
        precio = p;
        categoría = c;
    }
}
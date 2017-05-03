package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model;

/**
 * Created by Hernan on 29/04/2017.
 */

public class Usuario {

    private String Email;
    private String Password;

    private String Nombre;
    private String Apellido;
    private String DNI;


    public Usuario() { }

    public String getEmail(){ return Email; }

    public void setEmail(String email){
        Email = email;
    }

    public String getPassword(){
        return Password;
    }

    public void setPassword(String password){
        Password = password;
    }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    public String getApellido() { return Apellido; }

    public void setApellido(String apellido) { Apellido = apellido; }

    public String getDNI() { return DNI; }

    public void setDNI(String dni) { this.DNI = dni; }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model;

/**
 * Created by Hernan on 29/04/2017.
 */

public class Usuario {

    private String Email;
    private String Password;

    public Usuario() { }

    public String getEmail(){
        return Email;
    }

    public void setEmail(String email){
        Email = email;
    }

    public String getPassword(){
        return Password;
    }

    public void setPassword(String password){
        Password = password;
    }
}

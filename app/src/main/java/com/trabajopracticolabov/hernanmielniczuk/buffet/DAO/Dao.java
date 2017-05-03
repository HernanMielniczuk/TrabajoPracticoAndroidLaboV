package com.trabajopracticolabov.hernanmielniczuk.buffet.DAO;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hernan on 02/05/2017.
 */

public class Dao {

    private List<Usuario> usuarios;
    private static Dao dao;

    public List<Usuario> getUsuarios(){ return usuarios; }
    public void setUsuarios(List<Usuario> u) { usuarios = u; }

    public static Dao getDao() {
        if (dao == null) {
            dao = new Dao();
        }
        return dao;
    }

    private Dao(){
        usuarios = new ArrayList<>();
        usuarios.add(setUsuarioDefault());
    }

    private Usuario setUsuarioDefault(){
        Usuario u = new Usuario();
        u.setEmail("juanperez@gmail.com");
        u.setPassword("123456");
        return u;
    }








}

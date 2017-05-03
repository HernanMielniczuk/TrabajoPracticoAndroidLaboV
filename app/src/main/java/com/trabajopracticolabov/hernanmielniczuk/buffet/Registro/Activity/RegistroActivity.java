package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Dao;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;

public class RegistroActivity extends AppCompatActivity {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {return usuarios; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Dao dao = Dao.getDao();
        usuarios = dao.getUsuarios();
    }
}

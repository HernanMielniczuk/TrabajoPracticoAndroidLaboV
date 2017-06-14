package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Dao;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller.RegistroController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.RegistroListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.View.RegistroView;

import java.util.List;

import Utilities.ActionBarHelper;

public class RegistroActivity extends AppCompatActivity {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {return usuarios; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ActionBarHelper.invalidateActionBar(this);
        RegistroView view = new RegistroView(this);
        RegistroController controller = new RegistroController(new RegistroListener(view), this);
        view.setController(controller);
        view.setSignupListener(controller);

        Dao dao = Dao.getDao();
        usuarios = dao.getUsuarios();
    }
}

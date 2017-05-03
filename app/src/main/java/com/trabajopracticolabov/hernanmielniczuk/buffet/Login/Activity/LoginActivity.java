package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Dao;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View.LoginView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {return usuarios; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Dao dao = Dao.getDao();
        usuarios = dao.getUsuarios();

        LoginView v = new LoginView(this);
        LoginController c = new LoginController(new LoginListener(v), this);
        v.setLoginController(c);
    }
}

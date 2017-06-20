package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Dao;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.SignupListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View.LoginView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;

import Utilities.ActionBarHelper;
import Utilities.Globals;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBarHelper.invalidateActionBar(this);

        if(LoginController.isUserLoggedIn(this)){
            LoginController.irAMenu(this);
        } else {
            LoginView v = new LoginView(this);
            LoginController c = new LoginController(v, new LoginListener(v), new SignupListener(v), this);
        }
    }
}

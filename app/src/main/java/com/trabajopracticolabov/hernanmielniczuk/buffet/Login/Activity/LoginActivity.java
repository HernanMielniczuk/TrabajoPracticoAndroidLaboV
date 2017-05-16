package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Dao;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View.LoginView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;

import Utilities.ActionBarHelper;
import Utilities.Globals;

public class LoginActivity extends AppCompatActivity {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {return usuarios; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBarHelper.invalidateActionBar(this);

        if(isUserLoggedIn(this)){
            Intent intent = new Intent(this, MenuActivity.class);
            this.startActivity(intent);
        } else {

            Dao dao = Dao.getDao();
            usuarios = dao.getUsuarios();

            LoginView v = new LoginView(this);
            LoginController c = new LoginController(new LoginListener(v), this);
            v.setLoginController(c);
        }
    }

    public static boolean isUserLoggedIn(AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("config", MODE_PRIVATE);
        return (preferences.getString(Globals.EMAIL, null) != null && preferences.getString(Globals.PASSWORD, null) != null);
    }

    public void rememberUserLogin(String email, String password) {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        preferences.edit()
                .putString(Globals.EMAIL, email)
                .putString(Globals.PASSWORD, password)
                .apply();
    }

    public static void logout(AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("config", MODE_PRIVATE);
        preferences.edit()
                .remove(Globals.EMAIL)
                .remove(Globals.PASSWORD)
                .apply();
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginListener implements View.OnClickListener {

    private ILoginManager login;

    public  LoginListener(ILoginManager l) {
        login = l;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnIngresar){
           login.login();
        } else if (id == R.id.btnRegistrarme) {
                login.register();
            }
    }
}

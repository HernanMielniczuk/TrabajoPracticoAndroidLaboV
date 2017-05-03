package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginListener implements View.OnClickListener {

    private ILogin login;

    public  LoginListener(ILogin l) {
        login = l;
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnLoginIngresar){
           login.login();
        }
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.app.Activity;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;

import Utilities.InputValidator.InputValidator;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginController {

    private LoginListener listener;
    private LoginActivity activity;

    public LoginController(LoginListener l, LoginActivity a) {
        listener = l;
        activity = a;
    }

    public LoginListener getLoginListener(){
        return listener;
    }

    public Activity getActivity(){
        return activity;
    }

    public int login(String email, String password) {
        if(!InputValidator.isValidEmail(email)) return 1;
        if(password.trim().isEmpty()) return 2;

        boolean emailFound = false;
        int index = -1;

        for(Usuario u : activity.getUsuarios()) {
            if (u.getEmail().equals(email)) {
                emailFound = true;
                index = activity.getUsuarios().indexOf(u);
                break; }
        }
        if(!emailFound) return 3;

        if (!activity.getUsuarios().get(index).getPassword().equals(password)) return 4;

        //Login successful
        return 0;
    }
}

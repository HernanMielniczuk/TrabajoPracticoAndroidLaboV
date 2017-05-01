package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;

import Utilities.InputValidator.InputValidator;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginController {

    private LoginListener listener;
    private LoginActivity activity;

    public LoginController(LoginListener l) {
        listener = l;
    }
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
        if(!InputValidator.isValidEmail(email)){
            return 1;
        }
        if(password.trim().isEmpty()){
            return 2;
        }
        if (!activity.getUsuario().getEmail().equals(email)) {
            return 3;
        } else if (!activity.getUsuario().getPassword().equals(password)){
            return 4;
        }
        return 0; //Todo: pasar a la siguente Activity.
    }
}

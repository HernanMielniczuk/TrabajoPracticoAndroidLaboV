package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.SignupListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;

import org.json.JSONException;

import Utilities.InputValidator.InputValidator;
import Utilities.JSONParser.BuffetJSONParser;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginController implements Handler.Callback {

    private LoginListener loginListener;
    private SignupListener signupListener;
    private LoginActivity activity;
    private boolean emailFound;
    private boolean passwordsMatch;
    private Usuario u;

    public LoginController(LoginListener l, SignupListener s, LoginActivity a) {
        loginListener = l;
        signupListener = s;
        activity = a;
        emailFound = false;
        passwordsMatch = false;
    }

    public LoginListener getLoginListener(){
        return loginListener;
    }
    public SignupListener getSignupListener() { return signupListener; }

    public Activity getActivity(){
        return activity;
    }

    public int login(String email, String password) {
        if(!InputValidator.isValidEmail(email)) return 1;
        if(password.trim().isEmpty()) return 2;
        if(!buscarUsuario(email)) return 3;
        if(!validarPassword(email, password)) return 4;
        return 0; //Login successful
    }

    private boolean buscarUsuario(String email){
        Handler h = new Handler(this);
        BuscarUsuarioThread buscarUsuarioThread = new BuscarUsuarioThread(email, h);
        Thread thread = new Thread(buscarUsuarioThread, "HiloBuscarUsuario");
        thread.start();
        try {
            thread.join();
            Log.d("usuario", u != null ? u.getNombre() : "usuario en null");
            return emailFound;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validarPassword(String email, String password){
        Handler h = new Handler(this);
        ValidarUsuarioThread validarUsuarioThread = new ValidarUsuarioThread(email, password, h);
        Thread thread = new Thread(validarUsuarioThread, "HiloValidarUsuario");
        thread.start();
        try {
            thread.join();
            return passwordsMatch;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.arg1) {
            case 1: {
                BuffetJSONParser parser = new BuffetJSONParser();
                emailFound = true;
                try {
                    u = parser.parsearUsuarios(msg.obj.toString()).get(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                BuffetJSONParser parser = new BuffetJSONParser();
                try {
                    switch (parser.obtenerCodigoRetorno(msg.obj.toString())){
                        case 200:{
                            passwordsMatch = true;
                            break;
                        }
                        case 400:
                        case 500:
                        default:{
                            passwordsMatch = false;
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return true;
    }
}

package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.SignupListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View.LoginView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import org.json.JSONException;

import Utilities.BuffetMensajes.BuffetMensajes;
import Utilities.Globals;
import Utilities.InputValidator.InputValidator;
import Utilities.JSONParser.BuffetJSONParser;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginController implements Handler.Callback {

    private LoginView view;
    private LoginListener loginListener;
    private SignupListener signupListener;
    private LoginActivity activity;
    private boolean passwordsMatch;
    private Usuario u;
    private String mailLogin;
    private String passwordLogin;
    private BuffetMensajes mensaje;


    public LoginController(LoginView v, LoginListener l, SignupListener s, LoginActivity a) {
        view = v;
        loginListener = l;
        signupListener = s;
        activity = a;
        passwordsMatch = false;

        v.setLoginController(this);
        v.setSignupController(this);

        mensaje = new BuffetMensajes();
        mensaje.setBotonOk(R.string.btnOK);
        mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.login_main));
    }

    public LoginListener getLoginListener(){
        return loginListener;
    }
    public SignupListener getSignupListener() { return signupListener; }

    public Activity getActivity(){
        return activity;
    }

    public void login(String email, String password) {


        if(!InputValidator.isValidEmail(email)) { //return 1;
            mensaje.setTitulo(R.string.msgIncorrectDataTitle);
            mensaje.setMensaje(R.string.msgIncorrectMailFormat);
            mensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if(password.trim().isEmpty()) { // return 2;
            mensaje.setTitulo(R.string.msgIncorrectDataTitle);
            mensaje.setMensaje(R.string.msgNoPassword);
            mensaje.show(activity.getSupportFragmentManager(), "normal");
        } else {
            mailLogin = email;
            passwordLogin = password;
            buscarUsuario(email);
        }
        //if(!validarPassword(email, password)) return 4;
//Login successful
    }

    private void buscarUsuario(String email) {
        Handler handler = new Handler(this);
        Thread thread = new Thread(new BuscarUsuarioThread(email, handler), "HiloBuscarUsuario");
        thread.start();
    }

   /* private boolean buscarUsuario(String email){
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
    } */

    private void validarPassword(String email, String password) {
        Handler handler = new Handler(this);
        Thread thread = new Thread(new ValidarUsuarioThread(email, password, handler), "HiloValidarUsuario");
        thread.start();
    }

    public static boolean isUserLoggedIn(AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("config", MODE_PRIVATE);
        return (preferences.getString(Globals.EMAIL, null) != null && preferences.getString(Globals.PASSWORD, null) != null);
    }

    private void rememberUserLogin(String email, String password) {
        SharedPreferences preferences = activity.getSharedPreferences("config", MODE_PRIVATE);
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

    public static void irAMenu(LoginActivity a){
        Intent intent = new Intent(a, MenuActivity.class);
        a.startActivity(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.arg1) {
            case 1: {
                try {
                    BuffetJSONParser parser = new BuffetJSONParser();
                    if (parser.parsearUsuarios(msg.obj.toString()).size() == 1) {
                        validarPassword(mailLogin, passwordLogin);
                    } else {
                        mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                        mensaje.setMensaje(R.string.msgEmailNotFound);
                        mensaje.show(activity.getSupportFragmentManager(), "normal");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                //BuffetJSONParser parser = new BuffetJSONParser();
                //switch (parser.obtenerCodigoRetorno(msg.obj.toString())){

                /* el json devuelto no tiene un formato correcto, por eso pregunto usando 'contains'. */

                if(msg.obj.toString().contains("'codigo': 200")) {
                    if(view.isRememberMeCheckBoxChecked()){
                        rememberUserLogin(mailLogin, passwordLogin);
                    }
                    Intent intent = new Intent(activity, MenuActivity.class);
                    activity.startActivity(intent);
                } else {
                    mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                    mensaje.setMensaje(R.string.msgIncorrectPassword);
                    mensaje.show(activity.getSupportFragmentManager(), "normal");
                }
                break;
            }
        }
        return true;
    }
}

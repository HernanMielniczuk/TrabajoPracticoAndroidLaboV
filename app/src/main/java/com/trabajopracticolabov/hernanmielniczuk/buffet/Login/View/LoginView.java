package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.ILogin;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.ISignup;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;

import Utilities.BuffetMensajes.BuffetMensajes;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginView implements ILogin, ISignup {

    private LoginActivity activity;
    private LoginController controller;
    private EditText txtEmail;
    private EditText txtPassword;
    private CheckBox chkRecordarme;
    private Button btnLogin;
    private Button btnRegister;

    public LoginView(LoginActivity a){
        activity = a;
        txtEmail = (EditText) a.findViewById(R.id.txtLoginEmail);
        txtPassword = (EditText) a.findViewById(R.id.txtLoginClave);
        chkRecordarme = (CheckBox) a.findViewById(R.id.chkLoginRecordarme);
        btnLogin = (Button) a.findViewById(R.id.btnLoginIngresar);
        btnRegister = (Button) a.findViewById(R.id.btnLoginRegistrarme);
    }

    public void setLoginController(LoginController c){
        if (controller == null) controller = c;
        btnLogin.setOnClickListener(controller.getLoginListener());
    }

    public void setSignupController(LoginController c){
        if (controller == null) controller = c;
        btnRegister.setOnClickListener(controller.getSignupListener());
    }

    public boolean isRememberMeCheckBoxChecked() {
        return chkRecordarme.isChecked();
    }

    @Override
    public void login() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        controller.login(email, password);
      /*  switch (i) {
            case 0: {
                if(isRememberMeCheckBoxChecked()){
                    activity.rememberUserLogin(email, password);
                }
                Intent intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
                break;
            }
            case 1: {
                BuffetMensajes mensaje = new BuffetMensajes();
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgIncorrectMailFormat);
                mensaje.setBotonOk(R.string.btnOK);
                mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.login_main));
                mensaje.show(activity.getSupportFragmentManager(), "normal");
                break;
            }
            case 2: {
                BuffetMensajes mensaje = new BuffetMensajes();
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgNoPassword);
                mensaje.setBotonOk(R.string.btnOK);
                mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.login_main));
                mensaje.show(activity.getSupportFragmentManager(), "normal");
                break;
            }
            case 3: {
                BuffetMensajes mensaje = new BuffetMensajes();
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgEmailNotFound);
                mensaje.setBotonOk(R.string.btnOK);
                mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.login_main));
                mensaje.show(activity.getSupportFragmentManager(), "normal");
                break;
            }
            case 4: {
                BuffetMensajes mensaje = new BuffetMensajes();
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgIncorrectPassword);
                mensaje.setBotonOk(R.string.btnOK);
                mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.login_main));
                mensaje.show(activity.getSupportFragmentManager(), "normal");
                break;
            }
        } */
    }

    @Override
    public void signup() {
        Intent intent = new Intent(activity, RegistroActivity.class);
        activity.startActivity(intent);
    }
}

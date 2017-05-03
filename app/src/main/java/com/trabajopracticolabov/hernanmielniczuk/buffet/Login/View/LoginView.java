package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.widget.Toast.makeText;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.ILogin;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginView implements ILogin {

    private LoginActivity activity;
    private LoginController controller;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnRegister;

    public LoginView(LoginActivity a){
        activity = a;
        txtEmail = (EditText) a.findViewById(R.id.txtLoginEmail);
        txtPassword = (EditText) a.findViewById(R.id.txtLoginClave);
        btnLogin = (Button) a.findViewById(R.id.btnLoginIngresar);
        btnRegister = (Button) a.findViewById(R.id.btnLoginRegistrarme);
    }

    public void setLoginController(LoginController c){
        controller = c;
        btnLogin.setOnClickListener(controller.getLoginListener());
    }

    @Override
    public void login() {
        int i = controller.login(txtEmail.getText().toString(), txtPassword.getText().toString());
        switch (i) {
            case 0: {
                //Todo: Pasar a activity Menú.
                break;
            }
            case 1: {
                Toast toast = makeText(activity.getApplicationContext(), "El email ingresado no tiene un formato correcto.", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case 2: {
                Toast toast = makeText(activity.getApplicationContext(), "Debe ingresar una contraseña para acceder.", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case 3: {
                Toast toast = makeText(activity.getApplicationContext(), "El email ingresado no corresponde a un usuario activo.", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case 4: {
                Toast toast = makeText(activity.getApplicationContext(), "Contraseña incorrecta.", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
    }
}

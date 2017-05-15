package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import static android.widget.Toast.makeText;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.ILogin;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 29/04/2017.
 */

public class LoginView implements ILogin {

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
        controller = c;
        btnLogin.setOnClickListener(controller.getLoginListener());
    }

    public boolean isRememberMeCheckBoxChecked() {
        return chkRecordarme.isChecked();
    }

    @Override
    public void login() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        int i = controller.login(email, password);
        switch (i) {
            case 0: {
                if(isRememberMeCheckBoxChecked()){
                    activity.rememberUserLogin(email, password);
                }
                Intent intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
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

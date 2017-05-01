package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener.LoginListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.View.LoginView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

public class LoginActivity extends Activity {

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        usuario = new Usuario();
        setDefaultUserValues(usuario);

        LoginView v = new LoginView(this);
        LoginController c = new LoginController(new LoginListener(v), this);
        v.setLoginController(c);
    }

    private void setDefaultUserValues(Usuario u){
        u.setEmail("juanperez@gmail.com");
        u.setPassword("123456");
    }
}

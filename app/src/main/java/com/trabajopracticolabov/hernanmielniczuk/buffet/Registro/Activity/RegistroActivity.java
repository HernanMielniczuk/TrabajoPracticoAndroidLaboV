package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller.RegistroController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.RegistroListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.View.RegistroView;

import Utilities.ActionBarHelper;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ActionBarHelper.invalidateActionBar(this);
        RegistroView view = new RegistroView(this);
        RegistroController controller = new RegistroController(new RegistroListener(view), this);
        view.setController(controller);
        view.setSignupListener(controller);
    }
}

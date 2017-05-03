package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.View;

import android.app.Activity;
import android.widget.EditText;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.IRegistro;

/**
 * Created by Hernan on 03/05/2017.
 */

public class RegistroView implements IRegistro {

    private RegistroActivity activity;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtDni;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;

    public RegistroView(RegistroActivity a){
        activity = a;
        txtNombre = (EditText) a.findViewById(R.id.txtRegistroNombre);
        txtApellido = (EditText) a.findViewById(R.id.txtRegistroApellido);
        txtDni = (EditText) a.findViewById(R.id.txtRegistroDNI);
        txtEmail = (EditText) a.findViewById(R.id.txtRegistroEmail);
        txtPassword = (EditText) a.findViewById(R.id.txtRegistroClave);
        txtConfirmPassword = (EditText) a.findViewById(R.id.txtRegistroReingresoClave);
    }



    @Override
    public void registrar() {

    }
}

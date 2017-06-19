package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.View;

import android.widget.Button;
import android.widget.EditText;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller.RegistroController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.IRegistro;

/**
 * Created by Hernan on 03/05/2017.
 */

public class RegistroView implements IRegistro {

    private RegistroController controller;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtDni;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private Button btnRegistrar;

    public void setController(RegistroController c){
        controller = c;
    }

    public RegistroView(RegistroActivity a){
        txtNombre = (EditText) a.findViewById(R.id.txtRegistroNombre);
        txtApellido = (EditText) a.findViewById(R.id.txtRegistroApellido);
        txtDni = (EditText) a.findViewById(R.id.txtRegistroDNI);
        txtEmail = (EditText) a.findViewById(R.id.txtRegistroEmail);
        txtPassword = (EditText) a.findViewById(R.id.txtRegistroClave);
        txtConfirmPassword = (EditText) a.findViewById(R.id.txtRegistroReingresoClave);
        btnRegistrar = (Button) a.findViewById(R.id.btnRegistroRegistrarme);
    }

    public void setSignupListener(RegistroController c){
        btnRegistrar.setOnClickListener(c.getListener());
    }

    @Override
    public void registrar() {
        Usuario usuario = new Usuario();
        usuario.setNombre(txtNombre.getText().toString());
        usuario.setApellido(txtApellido.getText().toString());
        usuario.setDNI(txtDni.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setPassword(txtPassword.getText().toString());
        controller.registrar(usuario, txtConfirmPassword.getText().toString());
    }


}
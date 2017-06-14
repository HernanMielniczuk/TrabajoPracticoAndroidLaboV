package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.View;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller.RegistroController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.IRegistro;

import Utilities.BuffetMensajes.BuffetMensajes;

/**
 * Created by Hernan on 03/05/2017.
 */

public class RegistroView implements IRegistro {

    private RegistroActivity activity;
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
        activity = a;
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
        if(informarEventoRegistro(controller.registrar(usuario, txtConfirmPassword.getText().toString()))){
            activity.finish();
        }
    }

    private boolean informarEventoRegistro(int evento){
        boolean signupSuccess = false;
        BuffetMensajes mensaje = new BuffetMensajes();
        mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.registro_main));
        mensaje.setBotonOk(R.string.btnOK);
        switch (evento){
            case 0:{
                mensaje.setTitulo(R.string.msgSignupSuccessfulTitle);
                mensaje.setMensaje(R.string.msgSignupSuccessful);
                signupSuccess = true;
                break;
            }
            case 1:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgNoName);
                break;
            }
            case 2:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgNoSurname);
                break;
            }
            case 3:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgNoID);
                break;
            }
            case 4:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgIncorrectMailFormat);
                break;
            }
            case 5:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgIncorrectPasswordFormat);
                break;
            }
            case 6:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgPasswordsMissmatch);
                break;
            }
            case 7:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgIDAlreadyExists);
                break;
            }
            case 8:{
                mensaje.setTitulo(R.string.msgIncorrectDataTitle);
                mensaje.setMensaje(R.string.msgMailAlreadyExists);
                break;
            }
        }
        mensaje.show(activity.getSupportFragmentManager(), "normal");
        return signupSuccess;
    }
}
package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller;

import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.BuscarUsuarioThread;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.RegistroListener;

import Utilities.BuffetMensajes.BuffetMensajes;
import Utilities.InputValidator.InputValidator;

/**
 * Created by Hernan on 02/05/2017.
 */

public class RegistroController implements Handler.Callback {

    private RegistroListener listener;
    private RegistroActivity activity;
    private Usuario usuarioNuevo;
    private BuffetMensajes buffetMensaje;

    public RegistroController(RegistroListener l, RegistroActivity a) {
        listener = l;
        activity = a;
    }

    public RegistroListener getListener(){
        return listener;
    }

    public void registrar(Usuario usuario, String confirmacionClave) {

        buffetMensaje = new BuffetMensajes();
        buffetMensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.registro_main));
        buffetMensaje.setBotonOk(R.string.btnOK);

        if (usuario.getNombre().isEmpty()) { //Nombre vacío
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgNoName);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if (usuario.getApellido().isEmpty()) { //Apellido vacío
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgNoSurname);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if (usuario.getDNI().isEmpty()) { //DNI vacío
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgNoID);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if (!InputValidator.isValidEmail(usuario.getEmail())) { //Email inválido
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgIncorrectMailFormat);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if (!InputValidator.isValidPassword(usuario.getPassword())) { //Password inválido
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgIncorrectPasswordFormat);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else
        if (!usuario.getPassword().equals(confirmacionClave)) { //Validación password incorrecta
            buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
            buffetMensaje.setMensaje(R.string.msgPasswordsMissmatch);
            buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
        } else {
            usuarioNuevo = usuario;
            validarMailExistente(usuario.getEmail());
        }
    }

    private void validarMailExistente(String mail) {
        Handler handler = new Handler(this);
        Thread thread = new Thread(new BuscarUsuarioThread(mail, handler), "ThreadValidarUsuarioExistente");
        thread.start();
    }

    private void guardarUsuario(Usuario usuario) {
        Handler handler = new Handler(this);
        Thread thread = new Thread(new RegistroControllerThread(Conexion.getIPwithPort() + "/usuarios/nuevo", usuario, handler), "ThreadRegistroUsuario");
        thread.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
                if (msg.obj.toString().equals("[]")) {
                    guardarUsuario(usuarioNuevo);
                } else {
                    buffetMensaje.setTitulo(R.string.msgIncorrectDataTitle);
                    buffetMensaje.setMensaje(R.string.msgMailAlreadyExists);
                    buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
                }
        }
        if(msg.arg1 == 2) {
                if (msg.arg2 == 200) {
                    /*
                    buffetMensaje.setTitulo(R.string.msgSignupSuccessfulTitle);
                    buffetMensaje.setMensaje(R.string.msgSignupSuccessful);
                    buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
                    */
                    Toast.makeText(activity, R.string.msgSignupSuccessful, Toast.LENGTH_LONG).show();
                    activity.finish();
                } else {
                    buffetMensaje.setTitulo(R.string.msgErrorTitle);
                    buffetMensaje.setMensaje(R.string.msgErrorData);
                    buffetMensaje.show(activity.getSupportFragmentManager(), "normal");
                }
        }
        return true;
    }
}

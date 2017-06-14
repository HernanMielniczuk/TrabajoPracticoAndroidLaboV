package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Controller;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Activity.RegistroActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener.RegistroListener;

import java.lang.reflect.Field;

import Utilities.InputValidator.InputValidator;

/**
 * Created by Hernan on 02/05/2017.
 */

public class RegistroController {

    private RegistroListener listener;
    private RegistroActivity activity;

    public RegistroController(RegistroListener l, RegistroActivity a) {
        listener = l;
        activity = a;
    }

    public RegistroListener getListener(){
        return listener;
    }

    public int registrar(Usuario usuario, String confirmacionClave) {

        if (usuario.getNombre().isEmpty()) return 1; //Nombre vacío
        if (usuario.getApellido().isEmpty()) return 2; //Apellido vacío
        if (usuario.getDNI().isEmpty()) return 3; //DNI vacío
        if (!InputValidator.isValidEmail(usuario.getEmail())) return 4; //Email inválido
        if (!InputValidator.isValidPassword(usuario.getPassword())) return 5; //Password inválido
        if (!usuario.getPassword().equals(confirmacionClave)) return 6; //Validación password incorrecta

        for(Usuario u : activity.getUsuarios()) {
            if (u.getDNI().equals(usuario.getDNI())) {
                return 7; //DNI existente
            } else if (u.getEmail().equals(usuario.getEmail())) {
                return 8; //Email existente
            }
        }

        activity.getUsuarios().add(usuario);
        return 0; //Validación correcta
    }
}

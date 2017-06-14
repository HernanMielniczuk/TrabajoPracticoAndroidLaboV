package com.trabajopracticolabov.hernanmielniczuk.buffet.Registro.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 02/05/2017.
 */

public class RegistroListener implements View.OnClickListener {

    private IRegistro registro;

    public RegistroListener(IRegistro r) {
        registro = r;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegistroRegistrarme) {
            registro.registrar();
        }
    }
}
